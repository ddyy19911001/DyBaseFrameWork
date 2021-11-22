package com.app.mybaseframwork.data;

import com.app.mybaseframwork.base.BaseInfo;

import java.io.Serializable;

public class UpdateVersionInfo extends BaseInfo{

    /**
     * code : 10000
     * msg : 操作成功
     * data : {"version":{"ios":{"id":5,"name":"123","code":"asd","downLink":"http://www.baidu.com","content":"123123","isForce":0,"platformType":2},"android":{"id":2,"name":"V1.0.1","code":"2","downLink":"https://www.baidu.com","content":"安卓更新内容","isForce":0,"platformType":1}}}
     * totalTime : 0
     */
    private DataBean data;
    private String totalTime;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public static class DataBean implements Serializable {
        /**
         * version : {"ios":{"id":5,"name":"123","code":"asd","downLink":"http://www.baidu.com","content":"123123","isForce":0,"platformType":2},"android":{"id":2,"name":"V1.0.1","code":"2","downLink":"https://www.baidu.com","content":"安卓更新内容","isForce":0,"platformType":1}}
         */
        private String shareDomain;
        private String startUp;
        private SystemAgreementBean systemAgreementPojo;
        private VersionBean version;

        public SystemAgreementBean getSystemAgreementPojo() {
            return systemAgreementPojo;
        }

        public void setSystemAgreementPojo(SystemAgreementBean systemAgreementPojo) {
            this.systemAgreementPojo = systemAgreementPojo;
        }

        public String getStartUp() {
            return startUp;
        }

        public void setStartUp(String startUp) {
            this.startUp = startUp;
        }

        public String getShareDomain() {
            return shareDomain;
        }

        public void setShareDomain(String shareDomain) {
            this.shareDomain = shareDomain;
        }

        public VersionBean getVersion() {
            return version;
        }

        public void setVersion(VersionBean version) {
            this.version = version;
        }


        public static class SystemAgreementBean implements Serializable {

            /**
             * faq :
             * privacyAgreement :
             * reportAgreement :
             * userAgreement :
             * youngAgreement :
             */

            private String faq;
            private String privacyAgreement;
            private String reportAgreement;
            private String userAgreement;
            private String youngAgreement;

            public String getFaq() {
                return faq;
            }

            public void setFaq(String faq) {
                this.faq = faq;
            }

            public String getPrivacyAgreement() {
                return privacyAgreement;
            }

            public void setPrivacyAgreement(String privacyAgreement) {
                this.privacyAgreement = privacyAgreement;
            }

            public String getReportAgreement() {
                return reportAgreement;
            }

            public void setReportAgreement(String reportAgreement) {
                this.reportAgreement = reportAgreement;
            }

            public String getUserAgreement() {
                return userAgreement;
            }

            public void setUserAgreement(String userAgreement) {
                this.userAgreement = userAgreement;
            }

            public String getYoungAgreement() {
                return youngAgreement;
            }

            public void setYoungAgreement(String youngAgreement) {
                this.youngAgreement = youngAgreement;
            }
        }


        public static class VersionBean implements Serializable {
            /**
             * ios : {"id":5,"name":"123","code":"asd","downLink":"http://www.baidu.com","content":"123123","isForce":0,"platformType":2}
             * android : {"id":2,"name":"V1.0.1","code":"2","downLink":"https://www.baidu.com","content":"安卓更新内容","isForce":0,"platformType":1}
             */

            private IosBean ios;
            private AndroidBean android;

            public IosBean getIos() {
                return ios;
            }

            public void setIos(IosBean ios) {
                this.ios = ios;
            }

            public AndroidBean getAndroid() {
                return android;
            }

            public void setAndroid(AndroidBean android) {
                this.android = android;
            }

            public static class IosBean implements Serializable {
                /**
                 * id : 5
                 * name : 123
                 * code : asd
                 * downLink : http://www.baidu.com
                 * content : 123123
                 * isForce : 0
                 * platformType : 2
                 */

                private int id;
                private String name;
                private String code;
                private String downLink;
                private String content;
                private int isForce;
                private int platformType;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getDownLink() {
                    return downLink;
                }

                public void setDownLink(String downLink) {
                    this.downLink = downLink;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getIsForce() {
                    return isForce;
                }

                public void setIsForce(int isForce) {
                    this.isForce = isForce;
                }

                public int getPlatformType() {
                    return platformType;
                }

                public void setPlatformType(int platformType) {
                    this.platformType = platformType;
                }
            }





            public static class AndroidBean implements Serializable {
                /**
                 * id : 2
                 * name : V1.0.1
                 * code : 2
                 * downLink : https://www.baidu.com
                 * content : 安卓更新内容
                 * isForce : 0
                 * platformType : 1
                 */

                private int id;
                private String name;
                private int code;
                private String downLink;
                private String content;
                private int isForce;
                private int platformType;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getCode() {
                    return code;
                }

                public void setCode(int code) {
                    this.code = code;
                }

                public String getDownLink() {
                    return downLink;
                }

                public void setDownLink(String downLink) {
                    this.downLink = downLink;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getIsForce() {
                    return isForce;
                }

                public void setIsForce(int isForce) {
                    this.isForce = isForce;
                }

                public int getPlatformType() {
                    return platformType;
                }

                public void setPlatformType(int platformType) {
                    this.platformType = platformType;
                }
            }
        }
    }
}
