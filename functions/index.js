// Import function triggers from their respective submodules using ES module syntax
import {onRequest} from "firebase-functions/v2/https";
import logger from "firebase-functions/logger";
import express from "express";
import axios from "axios";


// WEBHOOK CONNECTION LISTENER
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
export const createPlaidLinkToken = onRequest(async (req, res) => {
  // Use environment variables or Firebase config for sensitive data
  const PLAID_CLIENT_ID = "66dbbd2a020d87001aba4e67"
  const PLAID_SECRET = "ee68bc1cbda56c95630d400c79d958"
  const PLAID_ENVIRONMENT = "sandbox"; // Change to 'development' or 'production' as needed

const randomUserId = Math.random().toString(36).substring(2, 15); // Temporary, unique ID for testing

//  const userId = req.body.userId; // Ensure you get a unique user ID from the client or generate one for each request

  try {
    const response = await axios.post(`https://${PLAID_ENVIRONMENT}.plaid.com/link/token/create`, {
      client_id: PLAID_CLIENT_ID,
      secret: PLAID_SECRET,
      user: {
        client_user_id: randomUserId,
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

    // Send the Plaid link token back to the client
    res.status(200).json(response.data);
  } catch (error) {
    logger.error("Error creating Plaid link token", { error: error.message });
    res.status(500).json({ error: "Failed to create Plaid link token" });
  }
});
