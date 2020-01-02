package com.example.admin.service.controller;

import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.gif.GIFImageReaderSpi;
import com.sun.imageio.plugins.gif.GIFImageWriter;
import com.sun.imageio.plugins.gif.GIFImageWriterSpi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;

@RestController
public class ImageController {

    //获取gif第一帧
    @GetMapping("/image/test")
    public String test() throws IOException {
        String imgPath = "C:\\Users\\ttdys\\Desktop\\pic\\g1.gif";
        String outPath = "C:\\Users\\ttdys\\Desktop\\pic\\g1.png";
        FileImageInputStream in = new FileImageInputStream(new File(imgPath));
        GIFImageReader ir = (GIFImageReader) new GIFImageReaderSpi().createReaderInstance();
        ir.setInput(in);
        GIFImageWriter iw = (GIFImageWriter) new GIFImageWriterSpi().createWriterInstance();
        FileImageOutputStream os = new FileImageOutputStream(new File(outPath));
        iw.setOutput(os);
        iw.write(ir.read(0));
        in.close();
        os.close();
        return "ok";
    }


}
