package com.example.decision_engine.util;

public class ResponseMessage {

    private ResponseMessage() {}

    public static class GlobalException {

        public static final String PARAMETER_MISMATCHED = "failed! Request contains invalid or missing parameters.";

        public static final String CONSTRAINT_VIOLATION = "failed! One or more request parameters failed validation.";

        public static final String INVALID_ARGUMENT = "failed! Invalid argument provided.";

        public static final String INVALID_STATE = "failed! Cannot perform this operation in the current resource state.";

        public static final String RESOURCE_NOT_FOUND = "failed! Requested resource not found.";

        public static final String OPERATION_NOT_SUPPORTED = "failed! This operation is not supported.";

        public static final String UNEXPECTED_ERROR = "failed! An unexpected error occurred. Please try again later.";

        public static final String RESOURCE_ALREADY_EXISTS = "failed! Resource already exists";

    }

    public static class Management {

        public static final String SUCCESS_CREATED_DECISION = "success! decision created";

        public static final String SUCCESS_FETCHED_DECISIONS = "success! decision fetched";

    }
}
