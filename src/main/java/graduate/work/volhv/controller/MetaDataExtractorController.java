package graduate.work.volhv.controller;

import graduate.work.volhv.service.MetaDataExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetaDataExtractorController {

    private final MetaDataExtractorService metaDataExtractorService;

    @Autowired
    public MetaDataExtractorController(MetaDataExtractorService metaDataExtractorService){
        this.metaDataExtractorService = metaDataExtractorService;
    }

}
