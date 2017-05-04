package ua.test.constants;

public final class Links {

    private Links() {}

    public static final class Uri {
        //general links
        public static final String HOME_PAGE = "/testing_system/";
        public static final String SIGN_IN_PAGE = "/testing_system/sign_in";
        public static final String SIGN_OUT_PAGE = "/testing_system/user/sign_out";
        public static final String REGISTRATION_PAGE = "/testing_system/registration";
        public static final String PROFILE_PAGE = "/testing_system/user/my_profile";
        public static final String TESTS_PAGE = "/testing_system/user/tests";
        public static final String CHANGE_PASSWORD_PAGE = "/testing_system/user/update_pass";
        public static final String CHANGE_EMAIL_PAGE = "/testing_system/user/update_email";

        //students' links
        public static final String START_TEST_PAGE = "/testing_system/student/start_test";
        public static final String STUDENT_TEST_PAGE = "/testing_system/student/test";
        public static final String END_TEST_PAGE = "/testing_system/student/test_end";
        public static final String ALL_STUDENT_RESULTS_PAGE = "/testing_system/student/results";
        public static final String TEST_RESULT_PAGE = "/testing_system/student/show_result";

        //tutors' links
        public static final String TUTOR_TEST_PAGE = "/testing_system/tutor/test";
        public static final String CREATE_QUESTION_PAGE = "/testing_system/tutor/create_question";
        public static final String ADD_QUESTION_PAGE = "/testing_system/tutor/add_question";
        public static final String DELETE_QUESTION_PAGE = "/testing_system/tutor/delete_question";
        public static final String RESULTS_FOR_TEST_PAGE = "/testing_system/tutor/results_for_test";
        public static final String CHANGE_STATE_TEST_PAGE = "/testing_system/tutor/change_test_state";
        public static final String ADD_TEST_PAGE = "/testing_system/tutor/add_test";
        public static final String EDIT_TEST_PAGE = "/testing_system/tutor/test_edit";
        public static final String DELETE_TEST_PAGE = "/testing_system/tutor/delete_test";
    }

    public static final class Jsp {
        public static final String MY_PROFILE_PAGE = "/pages/myProfile.jsp";
        public static final String SIGN_IN_PAGE = "/pages/signIn.jsp";
        public static final String REGISTRATION_PAGE = "/pages/registration.jsp";

        public static final String STUDENT_TEST_PAGE = "/pages/student/test.jsp";
        public static final String STUDENT_TEST_PAGES = "/pages/student/test.jsp";
        public static final String STUDENT_TEST_RESULT_PAGE = "/pages/student/test_result.jsp";
        public static final String STUDENT_RESULTS_PAGE = "/pages/student/results.jsp";

        public static final String TUTOR_NEW_QUESTION_PAGE = "/pages/tutor/new_question.jsp";
        public static final String TUTOR_TEST_PAGE = "/pages/tutor/test.jsp";
        public static final String TUTOR_TESTS_PAGE = "/pages/tutor/test.jsp";
        public static final String TUTOR_TEST_RESULTS = "/pages/tutor/test_results.jsp";

        public static final String ERROR404_PAGE = "/pages/errors/404.jsp";
    }

}
