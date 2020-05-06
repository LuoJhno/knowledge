package java_exception_wrapper;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

public class BusinessExceptionUtil {
    public static final int DEFAULT_EXCEPTION_CODE = -1;

    public static void throwBusinessException(int code, String message) {
        throw new BadRequestException(code, message);
    }

    /**
     * exceptionClass必须继承自 BadRequestException,否则不会抛出异常信息
     * 
     * @param <T>
     *
     * @param exceptionClass 异常的类信息
     * @param code           错误码
     * @param message        错误信息
     */
    public static <T> void throwBusinessException(Class<T> exceptionClass, int code, String message) {
        try {
            if (exceptionClass.isAssignableFrom(BadRequestException.class)) {
                Object o = exceptionClass.getConstructor(int.class, String.class).newInstance(code, message);
                if (o instanceof BadRequestException) {
                    throw (BadRequestException) o;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("exceptionClass does not extend BadRequestException");
        }


    }
    public static class Assert {
        public static void state(boolean expression, int code, String message) {
            if (!expression) {
                throwBusinessException(code, message);
            }
        }

        public static void state(boolean expression, Class<T> exceptionClass, int code, String message) {
            if (!expression) {
                throwBusinessException(exceptionClass, code, message);
            }
        }

        /**
         * @deprecated
         */
        @Deprecated
        public static void state(boolean expression) {
            state(expression, DEFAULT_EXCEPTION_CODE, "[Assertion failed] - this state invariant must be true");
        }

        public static void isTrue(boolean expression, int code, String message) {
            if (!expression) {
                throwBusinessException(code, message);
            }
        }

        public static void isTrue(boolean expression, Class<T> exceptionClass, int code, String message) {
            if (!expression) {
                throwBusinessException(exceptionClass, code, message);
            }
        }

        /**
         * @deprecated
         */
        @Deprecated
        public static void isTrue(boolean expression) {
            isTrue(expression, DEFAULT_EXCEPTION_CODE, "[Assertion failed] - this expression must be true");
        }

        public static void isNull(@Nullable Object object, int code, String message) {
            if (object != null) {
                throwBusinessException(code, message);
            }
        }

        public static void isNull(@Nullable Object object, Class<T> exceptionClass, int code, String message) {
            if (object != null) {
                throwBusinessException(exceptionClass, code, message);
            }
        }

        /**
         * @deprecated
         */
        @Deprecated
        public static void isNull(@Nullable Object object) {
            isNull(object, DEFAULT_EXCEPTION_CODE, "[Assertion failed] - the object argument must be null");
        }

        public static void notNull(@Nullable Object object, int code, String message) {
            if (object == null) {
                throwBusinessException(code, message);
            }
        }

        public static void notNull(@Nullable Object object, Class<T> exceptionClass, int code, String message) {
            if (object == null) {
                throwBusinessException(exceptionClass, code, message);
            }
        }

        /**
         * @deprecated
         */
        @Deprecated
        public static void notNull(@Nullable Object object) {
            notNull(object, DEFAULT_EXCEPTION_CODE, "[Assertion failed] - this argument is required; it must not be null");
        }


        public static void hasLength(@Nullable String text, int code, String message) {
            if (!StringUtils.hasLength(text)) {
                throwBusinessException(code, message);
            }
        }

        public static void hasLength(@Nullable String text, Class<T> exceptionClass, int code, String message) {
            if (!StringUtils.hasLength(text)) {
                throwBusinessException(exceptionClass, code, message);
            }
        }

        /**
         * @deprecated
         */
        @Deprecated
        public static void hasLength(@Nullable String text) {
            hasLength(text, DEFAULT_EXCEPTION_CODE, "[Assertion failed] - this String argument must have length; it must not be null or empty");
        }

        public static void hasText(@Nullable String text, int code, String message) {
            if (!StringUtils.hasText(text)) {
                throwBusinessException(code, message);
            }
        }

        public static void hasText(@Nullable String text, Class<T> exceptionClass, int code, String message) {
            if (!StringUtils.hasText(text)) {
                throwBusinessException(exceptionClass, code, message);
            }
        }

        /**
         * @deprecated
         */
        @Deprecated
        public static void hasText(@Nullable String text) {
            hasText(text, DEFAULT_EXCEPTION_CODE, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
        }

        public static void doesNotContain(@Nullable String textToSearch, String substring, int code, String message) {
            if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
                throwBusinessException(code, message);
            }
        }

        public static void doesNotContain(@Nullable String textToSearch, String substring, Class<T> exceptionClass, int code, String message) {
            if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
                throwBusinessException(exceptionClass, code, message);
            }
        }

        /**
         * @deprecated
         */
        @Deprecated
        public static void doesNotContain(@Nullable String textToSearch, String substring) {
            doesNotContain(textToSearch, substring, DEFAULT_EXCEPTION_CODE, "[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
        }

        public static void notEmpty(@Nullable Object[] array, int code, String message) {
            if (ObjectUtils.isEmpty(array)) {
                throwBusinessException(code, message);
            }
        }

        public static void notEmpty(@Nullable Object[] array, int code, Class<T> exceptionClass, String message) {
            if (ObjectUtils.isEmpty(array)) {
                throwBusinessException(exceptionClass, code, message);
            }
        }

        /**
         * @deprecated
         */
        @Deprecated
        public static void notEmpty(@Nullable Object[] array) {
            notEmpty(array, DEFAULT_EXCEPTION_CODE, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
        }

        public static void noNullElements(@Nullable Object[] array, int code, String message) {
            if (array != null) {

                for (Object element : array) {
                    if (element == null) {
                        throwBusinessException(code, message);
                    }
                }
            }

        }

        public static void noNullElements(@Nullable Object[] array, Class<T> exceptionClass, int code, String message) {
            if (array != null) {

                for (Object element : array) {
                    if (element == null) {
                        throwBusinessException(exceptionClass, code, message);
                    }
                }
            }

        }

        /**
         * @deprecated
         */
        @Deprecated
        public static void noNullElements(@Nullable Object[] array) {
            noNullElements(array, DEFAULT_EXCEPTION_CODE, "[Assertion failed] - this array must not contain any null elements");
        }

        public static void notEmpty(@Nullable Collection<?> collection, int code, String message) {
            if (CollectionUtils.isEmpty(collection)) {
                throwBusinessException(code, message);
            }
        }

        public static void notEmpty(@Nullable Collection<?> collection, Class<T> exceptionClass, int code, String message) {
            if (CollectionUtils.isEmpty(collection)) {
                throwBusinessException(exceptionClass, code, message);
            }
        }

        /**
         * @deprecated
         */
        @Deprecated
        public static void notEmpty(@Nullable Collection<?> collection) {
            notEmpty(collection, DEFAULT_EXCEPTION_CODE, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
        }

        public static void notEmpty(@Nullable Map<?, ?> map, int code, String message) {
            if (CollectionUtils.isEmpty(map)) {
                throwBusinessException(code, message);
            }
        }

        public static void notEmpty(@Nullable Map<?, ?> map, Class<T> exceptionClass, int code, String message) {
            if (CollectionUtils.isEmpty(map)) {
                throwBusinessException(exceptionClass, code, message);
            }
        }

        /**
         * @deprecated
         */
        @Deprecated
        public static void notEmpty(@Nullable Map<?, ?> map) {
            notEmpty(map, DEFAULT_EXCEPTION_CODE, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
        }

        public static void isInstanceOf(Class<?> type, @Nullable Object obj, int code, String message) {
            notNull(type, code, "Type to check against must not be null");
            if (!type.isInstance(obj)) {
                instanceCheckFailed(type, obj, code, message);
            }
        }

        public static void isInstanceOf(Class<?> type, @Nullable Object obj, Class<T> exceptionClass, int code, String message) {
            notNull(type, code, "Type to check against must not be null");
            if (!type.isInstance(obj)) {
                instanceCheckFailed(type, obj, exceptionClass, code, message);
            }
        }


        public static void isAssignable(Class<?> superType, @Nullable Class<?> subType, int code, String message) {
            notNull(superType, code, "Super type to check against must not be null");
            if (subType == null || !superType.isAssignableFrom(subType)) {
                assignableCheckFailed(superType, subType, code, message);
            }
        }

        public static void isAssignable(Class<?> superType, @Nullable Class<?> subType, Class<T> exceptionClass, int code, String message) {
            notNull(superType, code, "Super type to check against must not be null");
            if (subType == null || !superType.isAssignableFrom(subType)) {
                assignableCheckFailed(superType, subType, exceptionClass, code, message);
            }
        }


        private static void instanceCheckFailed(Class<?> type, @Nullable Object obj, int code, @Nullable String msg) {
            String result = getInstanceCheckFailedResult(type, obj, msg);
            throwBusinessException(code, result);
        }

        private static void instanceCheckFailed(Class<?> type, @Nullable Object obj, Class<T> exceptionClass, int code, @Nullable String msg) {
            String result = getInstanceCheckFailedResult(type, obj, msg);
            throwBusinessException(exceptionClass, code, result);
        }

        private static String getInstanceCheckFailedResult(Class<?> type, @Nullable Object obj, @Nullable String msg) {
            String className = obj != null ? obj.getClass().getName() : "null";
            String result = "";
            boolean defaultMessage = true;
            if (StringUtils.hasLength(msg)) {
                if (endsWithSeparator(msg)) {
                    result = msg + " ";
                } else {
                    result = messageWithTypeName(msg, className);
                    defaultMessage = false;
                }
            }

            if (defaultMessage) {
                result = result + "Object of class [" + className + "] must be an instance of " + type;
            }
            return result;
        }

        private static void assignableCheckFailed(Class<?> superType, @Nullable Class<?> subType, Class<T> exceptionClass, int code, @Nullable String msg) {
            String result = getAssignableCheckFailedResult(superType, subType, msg);

            throwBusinessException(exceptionClass, code, result);
        }

        private static void assignableCheckFailed(Class<?> superType, @Nullable Class<?> subType, int code, @Nullable String msg) {
            String result = getAssignableCheckFailedResult(superType, subType, msg);

            throwBusinessException(code, result);
        }

        private static String getAssignableCheckFailedResult(Class<?> superType, @Nullable Class<?> subType, @Nullable String msg) {
            String result = "";
            boolean defaultMessage = true;
            if (StringUtils.hasLength(msg)) {
                if (endsWithSeparator(msg)) {
                    result = msg + " ";
                } else {
                    result = messageWithTypeName(msg, subType);
                    defaultMessage = false;
                }
            }

            if (defaultMessage) {
                result = result + subType + " is not assignable to " + superType;
            }
            return result;
        }

        private static boolean endsWithSeparator(String msg) {
            return msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith(".");
        }

        private static String messageWithTypeName(String msg, @Nullable Object typeName) {
            return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
        }
    }
}