package com.epam.javase.t05;

/**
 * Created by aivanov on 2/28/2017.
 */
public enum Subject implements Grade {
    MATH {
        private final Integer maxGrade = 10;

        @Override
        public Integer getMaxGrade() {
            return maxGrade;
        };

        @Override
        public Class getGradeType() {
            return Integer.class;
        }
    },
    ENGLISH {
        private final Integer maxGrade = 100;

        @Override
        public Integer getMaxGrade() {
            return maxGrade;
        };

        @Override
        public Class getGradeType() {
            return Integer.class;
        }
    },
    CHEMISTRY {
        private final Integer maxGrade = 10;

        @Override
        public Integer getMaxGrade() {
            return maxGrade;
        };

        @Override
        public Class getGradeType() {
            return Integer.class;
        }
    },
    HISTORY {
        private final Double maxGrade = 5.0;

        @Override
        public Double getMaxGrade() {
            return maxGrade;
        };

        @Override
        public Class getGradeType() {
            return Double.class;
        }
    },
    SPANISH {
        private final Double maxGrade = 10.0;

        @Override
        public Double getMaxGrade() {
            return maxGrade;
        };

        @Override
        public Class getGradeType() {
            return Double.class;
        }
    },
    BIOLOGY {
        private final Double maxGrade = 100.0;

        @Override
        public Double getMaxGrade() {
            return maxGrade;
        }

        @Override
        public Class getGradeType() {
            return Double.class;
        }
    };
}

