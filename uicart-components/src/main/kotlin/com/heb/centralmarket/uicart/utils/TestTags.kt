package com.heb.centralmarket.uicart.utils

object TestTags {

    // AuthLandingButtonSection
    object AuthLanding {
        const val LOGIN_BUTTON = "loginButton"
        const val SIGNUP_BUTTON = "signupButton"
        const val GUEST_BUTTON = "guestButton"
    }

    // PostLoginFulfillmentSelectionScreen
    object HowWouldYouLikeToShop {
        const val IN_STORE = "inStoreButton"
        const val PICKUP = "pickupButton"
        const val DELIVERY = "deliveryButton"
        const val PRIMARY_BUTTON = "primaryButton"
        const val SECONDARY_BUTON = "secondaryButton"
        fun titleText(title: String) = "title_$title"
    }

    // UICLocationDetails
    object StoreSelect {
        const val STORE_SELECTION_ROW = "storeSelectRow"
        const val STORE_NAME = "storeName"
        const val RADIO_BUTTON = "radioButton"
    }

    object Fulfillment {
        fun fulfillmentType(id: String) = "fulfillmentType$id"

        const val HEADER = "header"
        const val LOCATION_NAME = "locationName"
        const val LOCATION_ADDRESS = "locationAddress"
        const val LOCATION_DETAILS = "locationDetails"
        const val CHANGE_BUTTON = "changeButton"
        const val SELECT_TIMESLOT_TEXT = "selectTimeSlotText"
        const val SELECT_TIMESLOT_DETAILS = "selectTimeSlotDETAILS"
        const val SAVE_BUTTON = "saveButton"
        const val BACK_BUTTON = "backButton"
    }

    object TimeSlot {
        const val BACK_BUTTON = "backButton"
        const val SAVE_BUTTON = "saveButton"
        const val HEADER = "header"
        const val DATE_CONTAINER = "dateContainer"
        const val DATE_ROW = "dateRow"
        const val DATE_DAY = "dateDay"
        const val DATE_PRICE = "datePrice"
        const val TIMESLOT_CONTAINER = "timeSlotContainer"
        const val TIMESLOT_ROW = "timeSlotRow"
        const val TIMESLOT_TIME = "timeSlotTime"
        const val TIMESLOT_PRICE = "timeSlotPrice"
        const val TIMESLOT_RADIO_BUTTON = "timeSlotRadioButton"
    }

    object DiscardModal {
        const val TITLE = "title"
        const val DISCARD_BUTTON = "discardButton"
        const val CANCEL_BUTTON = "cancelButton"
        const val X_BUTTON = "xButton"
    }

    object OrderAhead {
        const val EMPTY_CART_TEXT = "emptyCartText"
    }

    object AddressBook {
        const val SEARCH_BAR = "searchBar"
        const val SEARCH_ICON = "searchIcon"
        const val BACK_BUTTON = "backButton"
        const val TITLE = "title"
        const val SEARCH_RESULTS_CONTAINER = "searchResultsContainer"
        const val SEARCH_RESULTS_ROW = "searchResultsRow"
        const val SEARCH_RESULTS_ADDRESS_TEXT = "searchResultsAddressText"

        const val ADDRESS_TEXT = "addressText"
        const val CHANGE_BUTTON = "changeButton"
        const val SCROLL_CONTAINER = "scrollContainer"

        fun inputField(id: String) = "input$id"

        const val LOCATION_INPUT_FIELD = "locationInputField"
        const val PHONE_INPUT_FIELD = "phoneInputField"
        const val FIRST_NAME_INPUT_FIELD = "firstNameInputField"
        const val LAST_NAME_FIELD = "lastNameInputField"
        const val NOTES_INPUT_FIELD = "notesInputField"
        const val SAVE_BUTTON = "saveButton"

        const val ADDRESS_CONTAINER = "addressContainer"
        const val ADDRESS_DETAILS_TEXT = "addressDetailsText"
        const val ADDRESS_EDIT_BUTTON = "addressEditButton"
        const val ADDRESS_TRASH_BUTTON = "addressTrashButton"
        const val ADD_NEW_ADDRESS_TEXT = "addNewAddressText"
    }
}
