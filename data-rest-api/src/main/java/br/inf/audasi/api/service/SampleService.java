package br.inf.audasi.api.service;

import br.inf.audasi.domain.entity.Sample;
import br.inf.audasi.domain.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Service
public class SampleService {

    @Autowired
    private SampleRepository sampleRepository;

    public void save(Sample sample) {
        sampleRepository.save(sample);
    }

    public Iterable<Sample> findAll() {
        return sampleRepository.findAll();
    }
}
