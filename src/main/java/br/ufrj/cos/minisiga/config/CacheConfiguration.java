package br.ufrj.cos.minisiga.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(br.ufrj.cos.minisiga.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Aluno.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Aluno.class.getName() + ".turmas", jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Funcionario.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Disciplina.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Disciplina.class.getName() + ".turmas", jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Turma.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Turma.class.getName() + ".notas", jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Turma.class.getName() + ".listaesperas", jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Turma.class.getName() + ".inscritos", jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Turma.class.getName() + ".horarios", jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Horario.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Horario.class.getName() + ".turmas", jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.ListaEsperaAlocacao.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Nota.class.getName(), jcacheConfiguration);
            cm.createCache(br.ufrj.cos.minisiga.domain.Calendario.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
