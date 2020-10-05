package com.baeldung.apache.beam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

import java.util.Arrays;

/**
 * https://beam.apache.org/
 */
public class ApacheBeamExample {

    PipelineOptions options = PipelineOptionsFactory.create();
    Pipeline pipeline = Pipeline.create(options);

    PCollection<KV<String, Long>> wordCount = pipeline
            .apply("(1) Read all lines",
                    TextIO.read().from(inputFilePath))
            .apply("(2) Flatmap to a list of words",
                    FlatMapElements.into(TypeDescriptors.strings())
                            .via(line -> Arrays.asList(line.split("\\s"))))
            .apply("(3) Lowercase all",
                    MapElements.into(TypeDescriptors.strings())
                            .via(word -> word.toLowerCase()))
            .apply("(4) Trim punctuations",
                    MapElements.into(TypeDescriptors.strings())
                            .via(word -> trim(word)))
            .apply("(5) Filter stopwords",
                    Filter.by(word -> !isStopWord(word)))
            .apply("(6) Count words",
                    Count.perElement());


}
