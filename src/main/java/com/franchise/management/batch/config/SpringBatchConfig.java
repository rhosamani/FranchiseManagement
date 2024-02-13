package com.franchise.management.batch.config;

import com.franchise.management.batch.dto.Person;
import com.franchise.management.batch.listner.JobCompletionNotificationListener;
import com.franchise.management.batch.processor.PersonItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class SpringBatchConfig {

    @Bean
    public FlatFileItemReader<Person> reader(){
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names("firstName", "lastName")
                .targetType(Person.class)
                .build();
    }

    @Bean
    public PersonItemProcessor processor(){
        return new PersonItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Person> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Person>()
                .sql("")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step, JobCompletionNotificationListener listener){
        return new JobBuilder("importUserJob")
                .listener(listener)
                .start(step)
                .build();

    }

    @Bean
    public Step step(JobRepository jobRepository, DataSourceTransactionManager transactionManager, FlatFileItemReader<Person> reader,
                     PersonItemProcessor processor,
                     JdbcBatchItemWriter<Person> writer){
        return new StepBuilder("step")
                .<Person,Person> chunk(3)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
