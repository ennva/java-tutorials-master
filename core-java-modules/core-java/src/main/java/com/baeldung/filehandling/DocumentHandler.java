package com.baeldung.filehandling;

import com.groupdocs.annotation.localization.SupportedLocales;
import com.groupdocs.editor.utils.CultureInfo;
import com.groupdocs.parser.Parser;
import com.groupdocs.parser.data.MetadataItem;
import com.groupdocs.viewer.ViewerSettings;
import com.itextpdf.text.pdf.PdfReader;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import javax.activation.MimetypesFileTypeMap;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.Locale;

public class DocumentHandler {
    private static String PATH = "C:\\Users\\nephthali.djabon\\Downloads\\IAM-CTIE-011-VPN Access Request.pdf";
    private static String DOCPATH = "C:\\Users\\nephthali.djabon\\Downloads\\NDA_MiSa.docx";
    private static String ZIPPATH = "C:\\Users\\nephthali.djabon\\Desktop\\Misions\\Project 1106 – MS RDPS HIVE\\MiSa RDPS_HIVE_Appendix 4_Dynamic Document Models.zip";

    public static void main(String[] args) throws IOException {
        Path file = Paths.get(PATH);
        System.out.println("\n############### BASIC ATTRIBUTES #################");
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println("CreationTime: " + attr.creationTime());
        System.out.println("LastAccessTime: " + attr.lastAccessTime());
        System.out.println("LastModifiedTime: " + attr.lastModifiedTime());
        System.out.println("isDirectory: " + attr.isDirectory());
        System.out.println("isOther: " + attr.isOther());
        System.out.println("isRegularFile: " + attr.isRegularFile());
        System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
        System.out.println("size: " + attr.size());

        System.out.println("\n############### DOS ATTRIBUTES #################");
        DosFileAttributes dosAttr = Files.readAttributes(file, DosFileAttributes.class);
        System.out.println("isReadOnly is " + dosAttr.isReadOnly());
        System.out.println("isHidden is " + dosAttr.isHidden());
        System.out.println("isArchive is " + dosAttr.isArchive());
        System.out.println("isSystem is " + dosAttr.isSystem());

        /*
        System.out.println("\n############### POSIX File Permissions for UNIX #################");
        PosixFileAttributes perms = Files.readAttributes(file, PosixFileAttributes.class);
        System.out.format("%s %s %s%n",
                perms.owner().getName(),
                perms.group().getName(),
                PosixFilePermissions.toString(perms.permissions()));
         */
        System.out.println("\n############### File Owner #################");
        FileOwnerAttributeView view = Files.getFileAttributeView(file, FileOwnerAttributeView.class);
        System.out.println("File Owner: " + view.getOwner().getName());

        System.out.println("\n############### File ACL View #################");
        AclFileAttributeView aclView = Files.getFileAttributeView(file, AclFileAttributeView.class);
        aclView.getAcl().forEach(accEntry -> {
            String[] entryValues = accEntry.toString().split(":");
            System.out.println("Entry: " + entryValues[0] + ", values: " + entryValues[1]);
        });

        System.out.println("\n############### USER DEFINED View #################");
        UserDefinedFileAttributeView uView = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);
        String name = uView.name();
        System.out.println("User-defined-attr-name: " + name);
        System.out.println("MimeType: " + MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(file.toFile()));
        uView.list().forEach(attrName -> System.out.println(attrName));

        System.out.println("\n############### ITEXT #################");
        PdfReader reader = new PdfReader(PATH);
        System.out.println("Number of pages: " + reader.getNumberOfPages());
        System.out.println("PDF encrypted? " + reader.isEncrypted());
        reader.getInfo().forEach( (k,v) -> System.out.println(k + ": " + v) );

        System.out.println("\n############### PDFBOX #################");
        PDDocument document = Loader.loadPDF(file.toFile());
        PDDocumentInformation info = document.getDocumentInformation();
        System.out.println("PDF creation date: " + info.getCreationDate());
        System.out.println("PDF Modification date: " + info.getModificationDate());
        System.out.println("PDF author: " + info.getAuthor());
        System.out.println("PDF Creator: " + info.getCreator());
        System.out.println("PDF Producer: " + info.getProducer());
        System.out.println("PDF Title: " + info.getTitle());
        //System.out.println("PDF Metadata keys: " + info.getMetadataKeys());
        reader.close();

        System.out.println("\n############### GROUPDOCS #################");
        System.out.println(Locale.getDefault() + " is supported Locale? " + SupportedLocales.isLocaleSupported("en-US"));
        //Parser parser = new Parser(DOCPATH);
        System.out.println(String.format("DOCX info: SIZE=%s, FORMAT=%s, EXTENSION=%s", Parser.getFileInfo(DOCPATH).getSize(),Parser.getFileInfo(DOCPATH).getFileType().getFileFormat(),Parser.getFileInfo(DOCPATH).getFileType().getExtension() ));
        //parser.getMetadata().forEach(metadataItem -> System.out.println("Metadata Item: " + metadataItem.getName() + ", Metadata Value: " + metadataItem.getValue()));

        //parser.close();
    }
}
