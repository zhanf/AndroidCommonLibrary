package com.well_talent.cjdzblistening.common.app;

/**
 * Created by zhanf on 2018/4/16.
 */

public class RouterPath {

    public class Course {
        private static final String MODULE_APP = "/dzbListening";
        public static final String PATH_COURSE_LIST = MODULE_APP + "/courseList";
    }

    public class Login {
        private static final String MODULE_LOGIN = "/login";
        public static final String PATH_ENTER_NUMBER = MODULE_LOGIN + "/enterNumber";
        public static final String PATH_SINGLE_SING_ON = MODULE_LOGIN + "/singleSingOn";
    }

}
