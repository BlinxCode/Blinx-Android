// Import function triggers from their respective submodules using ES module syntax
import {onRequest} from "firebase-functions/v2/https";
import logger from "firebase-functions/logger";
import express from "express";
import axios from "axios";
import { initializeApp } from "firebase-admin/app";
import { getFirestore } from "firebase-admin/firestore";
import { onDocumentCreated } from "firebase-functions/v2/firestore";

//Initialize app
initializeApp();

// WEBHOOK CONNECTION LISTENER TO PLAID
const app = express();

// Middleware to automatically parse JSON request bodies
app.use(express.json());

// Define the webhook route
app.post("/plaidWebhook", (req, res) => {
  try {
    // Content-Type validation
    if (req.headers["content-type"] !== "application/json") {
      return res.status(400).send("Invalid. Expected application/json");
    }

    // Extract and validate the JSON payload
    const {webHookType, webHookCode, itemId} = req.body;

    if (!webHookType || !webHookCode || !itemId) {
      return res.status(400).send("Missing: Type, webHookCode, or item_id");
    }

    const validWebhookTypes = ["TRANSACTIONS", "AUTH"];
    if (!validWebhookTypes.includes(webHookType)) {
      return res.status(400).send("Invalid webHookType value");
    }

    // Log the webhook event
    logger.info("Plaid Webhook Event Received", { event: req.body });

    // Process the event based on type and code
    if (webHookType === "TRANSACTIONS" && webHookCode === "DEFAULT_UPDATE") {
      logger.info("Processing Transaction Update", { data: req.body });
    } else if (webHookType === "AUTH" && webHookCode === "AUTH_DATA_UPDATE") {
      logger.info("Processing Auth Update", { data: req.body });
    } else {
      logger.info("Unhandled webhook event code", {code: webHookCode});
    }

    // Respond to Plaid
    res.status(200).send("Webhook received");
  } catch (error) {
    logger.error("Error processing webhook", {error: error.message});
    res.status(500).send("Internal Server Error");
  }
});

// Export the Plaid webhook function
export const plaidWebhook = onRequest(app);

// END OF WEBHOOK CONNECTION LISTENER


// Plaid Link Token creation function
const db = getFirestore();

// Access your environment variables directly (you may need a workaround as V2 doesn’t support `functions.config()`)
const PLAID_CLIENT_ID = process.env.PLAID_CLIENT_ID; // Example of using Node environment variables
const PLAID_SECRET = process.env.PLAID_SECRET;
const PLAID_ENVIRONMENT = process.env.PLAID_ENVIRONMENT;

// Firestore trigger using V2 for when a document is created in the "plaid/{userId}" collection
export const createPlaidLinkTokenOnCreate = onDocumentCreated("plaid/{userId}", async (event) => {
    const userId = event.params.userId; // Extract userId from the document path
    const data = event.data?.data();

    if (data && data.createLink === "Yes") {
        try {
            const response = await axios.post(`https://${PLAID_ENVIRONMENT}.plaid.com/link/token/create`, {
                client_id: PLAID_CLIENT_ID,
                secret: PLAID_SECRET,
                user: {
                    client_user_id: userId,
                },
                client_name: "Blinx App",
                products: ["auth"],
                country_codes: ["US"],
                language: "en",
                webhook: "https://plaidwebhook-2mtyrazniq-uc.a.run.app",
                android_package_name: "com.android.blinxapp.debug"
            }, {
                headers: {
                    'Content-Type': 'application/json',
                }
            });

            console.log("Plaid link token created successfully:", response.data);

            // Update Firestore with the Plaid response data and set `createLink` to "No"
            await db.collection('plaid').doc(userId).update({
                linkToken: response.data.link_token,
                createdAt: new Date(),
                createLink: "No"
            });

        } catch (error) {
            console.error("Error creating Plaid link token:", error.message);
        }
    }
});