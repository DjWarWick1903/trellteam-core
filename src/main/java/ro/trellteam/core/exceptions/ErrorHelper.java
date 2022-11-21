package ro.trellteam.core.exceptions;

import java.util.HashMap;

/**
 * Class which contains every error code and it's description.
 */
public class ErrorHelper {

    private static HashMap<String, String> errorList;

    static {
        errorList = new HashMap<>();

        errorList.put("CORE_ERR_0", "An error has been encountered.");
        errorList.put("CORE_ERR_1", "Board was not found.");
        errorList.put("CORE_ERR_2", "Ticket was not found.");
        errorList.put("CORE_ERR_3", "Card log was not found.");
        errorList.put("CORE_ERR_4", "Required request input is missing or invalid.");
        errorList.put("CORE_ERR_5", "Required request body parameter is missing or invalid.");
        errorList.put("CORE_ERR_6", "An error encountered while creating a ticket.");
        errorList.put("CORE_ERR_7", "An error encountered while changing the ticket status.");
        errorList.put("CORE_ERR_8", "An error encountered while updating the ticket.");
        errorList.put("CORE_ERR_9", "An error encountered while assigning a user to the ticket.");
        errorList.put("CORE_ERR_10", "An error encountered while unnasigning a user from the ticket.");
        errorList.put("CORE_ERR_11", "Comment was not found.");
    }
}
