package ru.epam.javacore.lesson_13_sax_parser_recursion.homework.storage.initor.fileinitor.xml.sax;

import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.common.business.exception.checked.InitStorageException;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.common.solutions.utils.FileUtils;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.common.solutions.utils.xml.sax.XmlSaxUtils;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.storage.initor.fileinitor.BaseFileInitor;
import ru.epam.javacore.lesson_13_sax_parser_recursion.homework.transportation.domain.Transportation;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class XmlSaxFileDataInitor extends BaseFileInitor {

    private static final String FILE = "/ru/epam/javacore/lesson_13_sax_parser_recursion/initdata/xmldata.xml";

    @Override
    public void initStorage() throws InitStorageException {
        File file = null;
        try {
            file = getFileWithInitData();
            SAXParser parser = XmlSaxUtils.getParser();
            SaxHandler saxHandler = new SaxHandler();
            parser.parse(file, saxHandler);
            Map<String, Cargo> cargoMap = saxHandler.getCargoMap();
            Map<String, Carrier> carrierMap = saxHandler.getCarrierMap();
            List<ParsedTransportation> transportations = saxHandler.getTransportations();
            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());
            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
            persistTransportations(transportationList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitStorageException(e.getMessage());
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils.createFileFromResource("init-data", "lesson12", FILE);
    }
}

