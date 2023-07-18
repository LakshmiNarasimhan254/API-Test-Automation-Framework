package org.mln.libraries;



import net.javacrumbs.jsonunit.assertj.JsonAssert;
import org.json.JSONException;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mln.customexceptions.CustomException;
import org.mln.customexceptions.JsonException;
import org.mln.reports.ExtentLogger;

import java.util.ArrayList;
import java.util.List;


public final class JSONCustomAssert {

    private static final String ACTUALJSON = "Actual JSON : ";
    private static final String EXPECTEDJSON = "Actual JSON : ";

    private JSONCustomAssert() {
    }

    /**
     * Compares two JSON strings using JSON path and asserts their equality.
     *
     * @param actualJSON The actual JSON string to be compared.
     * @param expectedJSON The expected JSON string to be compared against.
     * @param path The JSON path to focus the comparison on.
     *               Pass an empty string ("") if no specific path needs to be specified.
     */
    public static void jsonCustomAssertEqualsWithPath(String actualJSON, String expectedJSON, String path) {
        if (!expectedJSON.equals("")) {
            assertThatJson(actualJSON)
                    .inPath(path)
                    .isEqualTo(expectedJSON);
        } else {
            assertThatJson(actualJSON).isEqualTo(expectedJSON);
        }
        ExtentLogger.info(ACTUALJSON + returnJSONString(actualJSON));
        ExtentLogger.info(EXPECTEDJSON + returnJSONString(expectedJSON));
    }

    /**
     * This method performs a custom JSON assertion by logging the actual JSON and its corresponding return JSON string.
     * It returns a `configurablejsonassert` object that can be used to perform assertions.
     *
     * @param actualJSON The actual JSON string to be asserted.
     * @return A `configurablejsonassert` object used for performing assertions.
     */
    public static JsonAssert.ConfigurableJsonAssert jsonCustomAssert(String actualJSON) {
        ExtentLogger.info(ACTUALJSON + returnJSONString(actualJSON));
        return assertThatJson(actualJSON);
    }

    /**
     * Asserts that the specified JSON string is an array at the specified path.
     *
     * @param actualJSON The actual JSON string to be validated.
     * @param path The path to the array within the JSON.
     */
    public static void jsonCustomAssertIsArray(String actualJSON, String path) {
        assertThatJson(actualJSON)
                .node(path).isArray();
    }

    /**
     * This method performs a custom JSON assertion by comparing the actual JSON string with the expected JSON string.
     *
     * @param actualJSON The actual JSON string to be compared.
     * @param expectedJSON The expected JSON string for comparison.
     */
    public static void jsonCustomAssertEquals(String actualJSON, String expectedJSON) {

        assertThatJson(actualJSON).isEqualTo(expectedJSON);
        ExtentLogger.info(ACTUALJSON + returnJSONString(actualJSON));
        ExtentLogger.info(EXPECTEDJSON + returnJSONString(expectedJSON));
    }

    /**
     * This method compares two JSON strings with the ability to ignore specified nodes.
     *
     * @param actualJSON The actual JSON string.
     * @param expectedJSON The expected JSON string.
     * @param ignoreNodes A list of nodes to be ignored during the comparison.
     */
    public static void jsonCustomAssertEqualsIgnoreNodes(String actualJSON, String expectedJSON, List<String> ignoreNodes) {
        ExtentLogger.info(ACTUALJSON + returnJSONString(actualJSON));
        if (!expectedJSON.equalsIgnoreCase("")) {
            actualJSON = removeIgnoredNodes(actualJSON, ignoreNodes.toArray(new String[0]));
            expectedJSON = removeIgnoredNodes(expectedJSON, ignoreNodes.toArray(new String[0]));
            assertThatJson(actualJSON).isEqualTo(expectedJSON);
        } else {
            assertThatJson(actualJSON).isEqualTo(expectedJSON);
        }

        ExtentLogger.info(EXPECTEDJSON + returnJSONString(expectedJSON));
    }

    /**
     * Compares two JSON strings and performs custom assertions by ignoring specified values for nodes.
     * Logs actual and expected JSON strings using ExtentLogger.
     *
     * @param actualJSON The JSON string to be compared.
     * @param expectedJSON The JSON string to compare against.
     * @param ignoreValueForNodes List of values to be ignored for specified nodes.
     */
    public static void jsonCustomAssertEqualsIgnoreValues(String actualJSON, String expectedJSON, ArrayList<String> ignoreValueForNodes) {
        ExtentLogger.info(ACTUALJSON + returnJSONString(actualJSON));
        if (!expectedJSON.equalsIgnoreCase("")) {
            actualJSON = populateDefaultValueForNodes(actualJSON, ignoreValueForNodes.toArray(new String[0]));
            expectedJSON = populateDefaultValueForNodes(expectedJSON, ignoreValueForNodes.toArray(new String[0]));
            assertThatJson(actualJSON).isEqualTo(expectedJSON);
        } else {
            assertThatJson(actualJSON).isEqualTo(expectedJSON);
        }
        ExtentLogger.info(EXPECTEDJSON + returnJSONString(expectedJSON));
    }

    /**
     * Compares two JSON strings and asserts their equality after removing certain nodes and populating default values.
     *
     * @param actualJSON           The actual JSON string.
     * @param expectedJSON         The expected JSON string.
     * @param ignoreNodes          A list of nodes to ignore while comparing.
     * @param ignoreValueForNodes  A list of nodes for which default values should be populated.
     */
    public static void jsonCustomAssertEqualsPartial(String actualJSON, String expectedJSON, ArrayList<String> ignoreNodes, ArrayList<String> ignoreValueForNodes) {
        ExtentLogger.info(ACTUALJSON + returnJSONString(actualJSON));
        if (!expectedJSON.equalsIgnoreCase("")) {
            actualJSON = removeIgnoredNodes(actualJSON, ignoreNodes.toArray(new String[0]));
            expectedJSON = removeIgnoredNodes(expectedJSON, ignoreNodes.toArray(new String[0]));

            actualJSON = populateDefaultValueForNodes(actualJSON, ignoreValueForNodes.toArray(new String[0]));
            expectedJSON = populateDefaultValueForNodes(expectedJSON, ignoreValueForNodes.toArray(new String[0]));


            assertThatJson(actualJSON).isEqualTo(expectedJSON);
        } else {
            assertThatJson(actualJSON).isEqualTo(expectedJSON);
        }
        ExtentLogger.info(EXPECTEDJSON + returnJSONString(expectedJSON));
    }
    /**

     Populates default values for specified nodes in the input JSON.
     @param inputJSON The input JSON string.
     @param ignoreValueForNodes An array of node names to ignore when populating default values.
     @return The modified JSON string with default values populated.
     @throws JsonException if there is an error in JSON processing.
     @throws CustomException if there is an error during the default value population. */
    private static String populateDefaultValueForNodes(String inputJSON, String[] ignoreValueForNodes) {
        String returnJSON;
        int i = 0;
        while (i < ignoreValueForNodes.length) { // Iterate through the list of ignore nodes
            try {
                JSONObject actualJSONObject = new JSONObject(inputJSON);
                traverseJSONObjectPopulateDefault(actualJSONObject, ignoreValueForNodes[i]);
                inputJSON = actualJSONObject.toString();
            }catch(JSONException e){
                throw new JsonException(e.getMessage());
            }
            catch (Exception e) {
                throw new CustomException(e.getMessage());
           }

            i++;
        }
        returnJSON = inputJSON;
        return returnJSON;
    }
    /**

     This method is a recursive function that traverses a JSON object and populates default values for specific nodes.

     @param jsonObject The current JSON object being traversed.

     @param path The path to the node in the JSON object, using dot notation.

     @throws JSONException If there are any issues with JSON parsing. */
    private static void traverseJSONObjectPopulateDefault(JSONObject jsonObject, String path) throws JSONException { //Recursive Function
        String[] pathArray = path.split("\\.");
        String currentNode = pathArray[0];

        //Get remaining path
        int i = 1;
        StringBuilder remainingPath = new StringBuilder();
        while (i < pathArray.length) {
            if (i == pathArray.length - 1) remainingPath.append(pathArray[i]);
            else remainingPath.append(pathArray[i]).append(".");
            i++;
        }

        if (!currentNode.contains("[")) { //Not an array
            if (0 == pathArray.length - 1) { //last object
                //jsonObject.remove(currentNode);
                if (jsonObject.has(currentNode)) {
                    jsonObject.put(currentNode, "<<Value_Comparison_Ignored>>");
                }
            } else {
                JSONObject childObject = jsonObject.getJSONObject(currentNode); //Get the child object
                traverseJSONObjectPopulateDefault(childObject, remainingPath.toString()); //recursive call
            }
        } else { //It is an array
            String strIndex = currentNode.substring(currentNode.indexOf("[") + 1, currentNode.indexOf("]"));
            String arrayName = currentNode.substring(0, currentNode.indexOf("["));
            JSONArray jsonArray = jsonObject.getJSONArray(arrayName);
            if (!strIndex.equalsIgnoreCase("*")) { //Traverse a specific Node
                int index = Integer.parseInt(currentNode.substring(currentNode.indexOf("[") + 1, currentNode.indexOf("]")));
                traverseJSONObjectPopulateDefault(jsonArray.getJSONObject(index), remainingPath.toString());
            } else {//Traverse the entire Array
                int intIndex = 0;
                while (intIndex < jsonArray.length()) {
                    traverseJSONObjectPopulateDefault(jsonArray.getJSONObject(intIndex), remainingPath.toString());
                    intIndex++;
                }
            }
        }
    }

    private static String removeIgnoredNodes(String inputJSON, String[] ignoreNodes) {
        String returnJSON;
        int i = 0;
        while (i < ignoreNodes.length) { // Iterate through the list of ignore nodes
            try {
                JSONObject actualJSONObject = new JSONObject(inputJSON);
                traverseJSONObject(actualJSONObject, ignoreNodes[i]);
                inputJSON = actualJSONObject.toString();
            }catch(JSONException e){
            throw new JsonException(e.getMessage());
        }
            catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

        i++;
        }
        returnJSON = inputJSON;
        return returnJSON;
    }

    private static void traverseJSONObject(JSONObject jsonObject, String path) throws JSONException { //Recursive Function
        String[] pathArray = path.split("\\.");
        String currentNode = pathArray[0];

        //Get remaining path
        int i = 1;
        StringBuilder remainingPath = new StringBuilder();
        while (i < pathArray.length) {
            if (i == pathArray.length - 1) {
                remainingPath.append(pathArray[i]);
            } else {
                remainingPath.append(pathArray[i]).append(".");
            }
            i++;
        }

        if (!currentNode.contains("[")) { //Not an array
            if (0 == pathArray.length - 1) { //last object
                jsonObject.remove(currentNode);
            } else {
                JSONObject childObject = jsonObject.getJSONObject(currentNode); //Get the child object
                traverseJSONObject(childObject, remainingPath.toString()); //recursive call
            }
        } else { //It is an array
            String strIndex = currentNode.substring(currentNode.indexOf("[") + 1, currentNode.indexOf("]"));
            String arrayName = currentNode.substring(0, currentNode.indexOf("["));
            JSONArray jsonArray = jsonObject.getJSONArray(arrayName);
            if (!strIndex.equalsIgnoreCase("*")) { //Traverse a specific Node
                int index = Integer.parseInt(currentNode.substring(currentNode.indexOf("[") + 1, currentNode.indexOf("]")));
                traverseJSONObject(jsonArray.getJSONObject(index), remainingPath.toString());
            } else {//Traverse the entire Array
                int intIndex = 0;
                while (intIndex < jsonArray.length()) {
                    traverseJSONObject(jsonArray.getJSONObject(intIndex), remainingPath.toString());
                    intIndex++;
                }
            }
        }
    }
    private static String returnJSONString(String jsonString){
        try {
            return new JSONObject(jsonString).toString(4);
        } catch (JSONException e) {
            throw new JsonException(e.getMessage());
        }
    }

}
