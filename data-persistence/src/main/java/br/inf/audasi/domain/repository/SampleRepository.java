package br.inf.audasi.domain.repository;

import br.inf.audasi.domain.entity.Sample;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Repository
public class SampleRepository {

    private List<Sample> samples = new ArrayList<>();

    public void save(Sample sample) {
        samples.add(sample);
    }

    public List<Sample> findAll() {
        return samples;
    }
}
