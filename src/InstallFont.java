import java.awt.*;
import java.io.*;

public class InstallFont {
    File mediumFontFile;
    File boldFontFile;
    File lightFontFile;
    public InstallFont() {
        mediumFontFile = new File("fonts/" + "GmarketSansTTFMedium.ttf" );
        boldFontFile = new File("fonts/" + "GmarketSansTTFBold.ttf" );
        lightFontFile = new File("fonts/" + "GmarketSansTTFLight.ttf" );
    }
    public Font mediumFont(float _size){
        try {
            return Font.createFont(Font.TRUETYPE_FONT, mediumFontFile).deriveFont(Font.PLAIN,_size);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Font boldFont(float _size){
        try {
            return Font.createFont(Font.TRUETYPE_FONT, boldFontFile).deriveFont(Font.PLAIN,_size);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Font lightFont(float _size){
        try {
            return Font.createFont(Font.TRUETYPE_FONT, lightFontFile).deriveFont(Font.PLAIN,_size);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
