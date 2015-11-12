package br.inf.audasi.api.controller;

import br.inf.audasi.api.service.SampleService;
import br.inf.audasi.domain.entity.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author renatomoitinhodias@gmail.com
 */
@RestController
@RequestMapping(value = "/api", produces = {"application/json;charset=UTF-8"})
public class SampleValidatorController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping(value = "/samples", method = RequestMethod.GET)
    public Iterable<Sample> getSamples() {
        return sampleService.findAll();
    }

    @RequestMapping(value = "/samples", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveSample(@Valid @RequestBody Sample sample) {
        sampleService.save(sample);
    }
}
