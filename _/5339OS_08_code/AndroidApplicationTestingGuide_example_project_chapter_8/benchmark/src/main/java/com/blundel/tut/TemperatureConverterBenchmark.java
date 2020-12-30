package com.blundel.tut;

import com.blundell.tut.TemperatureConverter;
import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TemperatureConverterBenchmark {

    public static void main(String[] args) {
        CaliperMain.main(CelsiusToFahrenheitBenchmark.class, args);
    }

    public static class CelsiusToFahrenheitBenchmark {

        private static final double MULTIPLIER = 10;

        @Param({"1", "10", "100"})
        int total;

        private List<Double> temperatures = new ArrayList<Double>();

        @BeforeExperiment
        public void setUp() throws Exception {
            temperatures.clear();
            generateRandomTemperatures(total);
        }

        private void generateRandomTemperatures(int total) {
            Random r = new Random(System.currentTimeMillis());
            for (int i = 0; i < total; i++) {
                double randomTemperature = MULTIPLIER * r.nextGaussian();
                temperatures.add(randomTemperature);
            }
        }

        @Benchmark
        public void timeCelsiusToFahrenheit(int reps) {
            for (int i = 0; i < reps; i++) {
                for (double t : temperatures) {
                    TemperatureConverter.celsiusToFahrenheit(t);
                }
            }
        }
    }
}
