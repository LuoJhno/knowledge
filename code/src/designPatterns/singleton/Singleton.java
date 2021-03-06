package designPatterns.singleton;

public class Singleton {

    /**
     * 饱汉模式
     */
    public static class SingletonV1 {
        private SingletonV1() {
        }

        private static SingletonV1 singletonV1 = new SingletonV1();

        public static SingletonV1 getInstance() {
            return singletonV1;
        }
    }

    /**
     * 饿汉模式
     */
    public static class SingletonV2 {
        private SingletonV2() {
        }

        private static SingletonV2 singletonV2;

        public static SingletonV2 getInstance() {
            if (singletonV2 == null) {
                singletonV2 = new SingletonV2();
            }
            return singletonV2;
        }
    }

    /**
     * 静态方法块模式
     */
    public static class SingletonV3 {
        private SingletonV3() {
        }

        private static SingletonV3 singletonV3;

        static {
            singletonV3 = new SingletonV3();
        }

        public static SingletonV3 getInstance() {
            return singletonV3;
        }
    }

    /**
     * 静态内部类模式
     */
    public static class SingletonV4 {
        private SingletonV4() {
        }

        public static class InnerSingletonV4 {
            public static SingletonV4 singletonV4 = new SingletonV4();
        }

        public static SingletonV4 getInstance() {
            return InnerSingletonV4.singletonV4;
        }
    }

    /**
     * 线程安全模式
     */
    public static class SingletonV5 {
        private SingletonV5() {
        }

        private static SingletonV5 singletonV5;

        public static SingletonV5 getInstance() {
            synchronized (SingletonV5.class) {
                if (singletonV5 == null) {
                    singletonV5 = new SingletonV5();
                }
            }
            return singletonV5;
        }
    }

    /**
     * 双重校验锁模式
     */
    public static class SingletonV6 {
        private SingletonV6() {
        }

        private static SingletonV6 singletonV6;

        public static SingletonV6 getInstance() {
            if (singletonV6 == null) {
                synchronized (SingletonV6.class) {
                    if (singletonV6 == null) {
                        singletonV6 = new SingletonV6();
                    }
                }
            }
            return singletonV6;
        }
    }

    /**
     * 枚举模式
     */
    public static class SingletonFactory {
        private enum SingletonEnum {
            SingletonV7;

            private SingletonV7 singleton;

            private SingletonEnum() {
                singleton = new SingletonV7();
            }

            public SingletonV7 getInstance() {
                return singleton;
            }

        }

        public static SingletonV7 getInstance() {
            return SingletonEnum.SingletonV7.getInstance();
        }

    }

    class SingletonV7 {
        public SingletonV7() {
        }
    }

}