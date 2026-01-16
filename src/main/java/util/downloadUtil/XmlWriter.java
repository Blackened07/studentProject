package util.downloadUtil;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import model.FullData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public final class XmlWriter {

    private static final JAXBContext CONTEXT;
    private static final Logger logger = LoggerFactory.getLogger(XmlWriter.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH-mm");
    private static final String FILE_NAME = "_xml_result.xml";

    static {
        try {
            CONTEXT = JAXBContext.newInstance(FullData.class);
        } catch (JAXBException e) {
            logger.error("Ошибка создания JAXBContext");
            throw new RuntimeException(e);
        }
    }

    private XmlWriter() {
    }

    public static void writeToXml(FullData data) {
        logger.info("Start marshalling");

        try {
            Marshaller marshaller = CONTEXT.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            String fileName = LocalDateTime.now().format(FORMATTER) + FILE_NAME;

            Path fullPath = Paths.get(PropertiesUtil.getProperty(PropertiesUtil.XML_PATH)).resolve(fileName);
            Optional.ofNullable(fullPath.getParent())
                    .ifPresent(p -> {
                        try {
                            createXmlDir(p);
                        } catch (IOException e) {
                            logger.error("Ошибка записи");
                        }
                    });

            marshaller.marshal(data, fullPath.toFile());

        } catch (JAXBException e) {
            logger.error("Marshalling ERROR!!! {}", e.getMessage());
            return;
        }

        logger.info("Marshalling finished");
    }


    private static void createXmlDir(Path dirPath) throws IOException {
        try {
            Files.createDirectories(dirPath);
            logger.info("Directory created successfully");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

}
