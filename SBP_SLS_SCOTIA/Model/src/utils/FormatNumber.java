package utils;

import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;

import java.util.Locale;


import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.LocaleContext;
import oracle.jbo.common.DefLocaleContext;
import oracle.jbo.format.DefaultNumberFormatter;
import oracle.jbo.format.FormatErrorException;
import oracle.jbo.format.UnknownLocaleException;

public class FormatNumber extends DefaultNumberFormatter {

    private static ADFLogger _logger =
        ADFLogger.createADFLogger(FormatNumber.class);


    private static oracle.jbo.common.DefLocaleContext ctx =
        new DefLocaleContext(Locale.ENGLISH);
    private DecimalFormatSymbols mSymbols =
        new DecimalFormatSymbols(ctx.getLocale());

    public FormatNumber() {
        super();
    }
    
    public static Number formatearMonto(String monto){
        
        Number r = 0;
        String mont = "0";
        try{
            double amount = Double.parseDouble(monto);
            DecimalFormat formatter = new DecimalFormat("#,###");
            mont = formatter.format(amount);
            r = Integer.parseInt(mont);
        } catch(Exception e){
            
        }
        

        return r;
    }

    public String format(String mask,
                         Object object) throws FormatErrorException {
        String formattedString = "";

        if (mask.compareTo("") == 0) {
            mask = " ";
        }

        DecimalFormat decimalFormat = getDecimalFormat();
        decimalFormat.applyPattern(mask);
        if (object != null && (object instanceof String) == false) {
            _logger.finest("OBJECT TO FORMAT: " + object + ", TYPE: " +
                           object.getClass());
            if (object instanceof BigDecimal) {
                formattedString = decimalFormat.format(object);
            } else {
                BigDecimal formatThis = new BigDecimal(object.toString());
                formattedString = decimalFormat.format(formatThis);
            }

            _logger.finest("FORMATTED RESULT: " + formattedString);
        }
        return (formattedString);
    }

    private DecimalFormat getDecimalFormat() {
        return new DecimalFormat("", mSymbols);
    }

    public LocaleContext getLocale() {
        return super.getLocale();
    }

    public Object parse(String mask, String number) throws ParseException {
        _logger.finest("OBJECT TO PARSE: " + number);
        Object obj = this.parseObj(mask, number);
        _logger.finest("PARSED OBJECT: " + obj);
        //if the value wasn't parsed into a Number
        if (obj == null) {
            

            _logger.finest("The format is incorrect.  " +obj);
            throw new RuntimeException("The format is incorrect. The format of the number must match this pattern: " +
                                       mask);
        }

        int[] maskPrecisionAndScale = getPrecisionAndScale(mask, true);
        int[] numberPrecisionAndScale = getPrecisionAndScale(number, false);

        if (numberPrecisionAndScale[0] > maskPrecisionAndScale[0] ||
            numberPrecisionAndScale[1] > maskPrecisionAndScale[1]) {

            _logger.finest("The format is incorrect2.  " +obj);
            throw new RuntimeException("The format is incorrect. The format of the number must match this pattern: " +
                                       mask);
            
        }
        return obj;
    }

    public Object parseObj(String formatString,
                           String parseThisString) throws ParseException {
        if (formatString.compareTo("") == 0)
            formatString = " ";

        DecimalFormat format = getDecimalFormat();
        format.applyPattern(formatString);
        format.setParseBigDecimal(true);

        return (format.parse(parseThisString, new ParsePosition(0)));
    }


    private int[] getPrecisionAndScale(String value, boolean isMask) {
        //check the precision
        value = value.trim();
        value = value.replaceAll(",", ""); //removing groupping symbol
        if (value.startsWith("-") || value.startsWith("+")) {
            value = value.substring(1);
        }
        int[] digits = new int[] { value.length(), 0 };

        if (value.indexOf(".") > 0) {
            if (!isMask) {
                while (value.startsWith("0")) {
                    value = value.substring(1);
                }
                while (value.endsWith("0")) {
                    value = value.substring(0, value.length() - 1);
                }
            }

            digits[0] = value.indexOf(".");
            digits[1] = value.length() - value.indexOf(".") - 1;
        }
        return digits;
    }

    //ignoring user locale. defaulting to English

    public void setLocale(LocaleContext localeContext) throws UnknownLocaleException {
        super.setLocale(ctx);
    }
}
