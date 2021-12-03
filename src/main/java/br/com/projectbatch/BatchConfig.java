package br.com.projectbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Enilson Filho
 */
@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloWorld() {
        return jobBuilderFactory
                .get("imprimeHelloWorldJob")
                .start(imprimeHelloWolrdStep())
                .build();
    }

    public Step imprimeHelloWolrdStep() {
        return stepBuilderFactory
                .get("imprimeHelloWorldStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
                        System.out.println("Hello World");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

}
