import globals from "globals";

export default [
  {
    // Specify language options
    languageOptions: {
      ecmaVersion: 2022,
      globals: {
        ...globals.es2022,
        ...globals.node,
      },
    },
    // Mimic ESLint recommended settings manually
    rules: {
      "no-restricted-globals": ["error", "name", "length"],
      "prefer-arrow-callback": "error",
      "quotes": ["error", "double", { "allowTemplateLiterals": true }],
      "no-unused-vars": "warn",
      "no-undef": "error",
      "eqeqeq": "error",
      "curly": "error",
      "no-console": "warn",
    },
    // Override for test files
    files: ["**/*.spec.*"],
    languageOptions: {
      globals: {
        ...globals.mocha,
      },
    },
    rules: {
      // Specific rules for test files if needed
    },
  },
];
