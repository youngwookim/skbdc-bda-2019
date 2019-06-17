/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.example.avro;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
/** Avro schema for IEX Trading API */
@org.apache.avro.specific.AvroGenerated
public class IexTrading extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 1265079371784499269L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"IexTrading\",\"namespace\":\"com.example.avro\",\"doc\":\"Avro schema for IEX Trading API\",\"fields\":[{\"name\":\"symbol\",\"type\":\"string\",\"doc\":\"Stock Symbol\"},{\"name\":\"companyName\",\"type\":[\"null\",\"string\"],\"doc\":\"Company Name\",\"avro.java.string\":\"String\"},{\"name\":\"primaryExchange\",\"type\":[\"null\",\"string\"],\"avro.java.string\":\"String\"},{\"name\":\"sector\",\"type\":[\"null\",\"string\"],\"avro.java.string\":\"String\"},{\"name\":\"calculationPrice\",\"type\":[\"null\",\"string\"],\"avro.java.string\":\"String\"},{\"name\":\"open\",\"type\":[\"null\",\"double\"]},{\"name\":\"openTime\",\"type\":[\"null\",\"long\"]},{\"name\":\"close\",\"type\":[\"null\",\"double\"]},{\"name\":\"closeTime\",\"type\":[\"null\",\"long\"]},{\"name\":\"high\",\"type\":[\"null\",\"double\"]},{\"name\":\"low\",\"type\":[\"null\",\"double\"]},{\"name\":\"latestPrice\",\"type\":[\"null\",\"double\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /** Stock Symbol */
  @Deprecated public java.lang.CharSequence symbol;
  /** Company Name */
  @Deprecated public java.lang.CharSequence companyName;
  @Deprecated public java.lang.CharSequence primaryExchange;
  @Deprecated public java.lang.CharSequence sector;
  @Deprecated public java.lang.CharSequence calculationPrice;
  @Deprecated public java.lang.Double open;
  @Deprecated public java.lang.Long openTime;
  @Deprecated public java.lang.Double close;
  @Deprecated public java.lang.Long closeTime;
  @Deprecated public java.lang.Double high;
  @Deprecated public java.lang.Double low;
  @Deprecated public java.lang.Double latestPrice;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public IexTrading() {}

  /**
   * All-args constructor.
   * @param symbol Stock Symbol
   * @param companyName Company Name
   * @param primaryExchange The new value for primaryExchange
   * @param sector The new value for sector
   * @param calculationPrice The new value for calculationPrice
   * @param open The new value for open
   * @param openTime The new value for openTime
   * @param close The new value for close
   * @param closeTime The new value for closeTime
   * @param high The new value for high
   * @param low The new value for low
   * @param latestPrice The new value for latestPrice
   */
  public IexTrading(java.lang.CharSequence symbol, java.lang.CharSequence companyName, java.lang.CharSequence primaryExchange, java.lang.CharSequence sector, java.lang.CharSequence calculationPrice, java.lang.Double open, java.lang.Long openTime, java.lang.Double close, java.lang.Long closeTime, java.lang.Double high, java.lang.Double low, java.lang.Double latestPrice) {
    this.symbol = symbol;
    this.companyName = companyName;
    this.primaryExchange = primaryExchange;
    this.sector = sector;
    this.calculationPrice = calculationPrice;
    this.open = open;
    this.openTime = openTime;
    this.close = close;
    this.closeTime = closeTime;
    this.high = high;
    this.low = low;
    this.latestPrice = latestPrice;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return symbol;
    case 1: return companyName;
    case 2: return primaryExchange;
    case 3: return sector;
    case 4: return calculationPrice;
    case 5: return open;
    case 6: return openTime;
    case 7: return close;
    case 8: return closeTime;
    case 9: return high;
    case 10: return low;
    case 11: return latestPrice;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: symbol = (java.lang.CharSequence)value$; break;
    case 1: companyName = (java.lang.CharSequence)value$; break;
    case 2: primaryExchange = (java.lang.CharSequence)value$; break;
    case 3: sector = (java.lang.CharSequence)value$; break;
    case 4: calculationPrice = (java.lang.CharSequence)value$; break;
    case 5: open = (java.lang.Double)value$; break;
    case 6: openTime = (java.lang.Long)value$; break;
    case 7: close = (java.lang.Double)value$; break;
    case 8: closeTime = (java.lang.Long)value$; break;
    case 9: high = (java.lang.Double)value$; break;
    case 10: low = (java.lang.Double)value$; break;
    case 11: latestPrice = (java.lang.Double)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'symbol' field.
   * @return Stock Symbol
   */
  public java.lang.CharSequence getSymbol() {
    return symbol;
  }

  /**
   * Sets the value of the 'symbol' field.
   * Stock Symbol
   * @param value the value to set.
   */
  public void setSymbol(java.lang.CharSequence value) {
    this.symbol = value;
  }

  /**
   * Gets the value of the 'companyName' field.
   * @return Company Name
   */
  public java.lang.CharSequence getCompanyName() {
    return companyName;
  }

  /**
   * Sets the value of the 'companyName' field.
   * Company Name
   * @param value the value to set.
   */
  public void setCompanyName(java.lang.CharSequence value) {
    this.companyName = value;
  }

  /**
   * Gets the value of the 'primaryExchange' field.
   * @return The value of the 'primaryExchange' field.
   */
  public java.lang.CharSequence getPrimaryExchange() {
    return primaryExchange;
  }

  /**
   * Sets the value of the 'primaryExchange' field.
   * @param value the value to set.
   */
  public void setPrimaryExchange(java.lang.CharSequence value) {
    this.primaryExchange = value;
  }

  /**
   * Gets the value of the 'sector' field.
   * @return The value of the 'sector' field.
   */
  public java.lang.CharSequence getSector() {
    return sector;
  }

  /**
   * Sets the value of the 'sector' field.
   * @param value the value to set.
   */
  public void setSector(java.lang.CharSequence value) {
    this.sector = value;
  }

  /**
   * Gets the value of the 'calculationPrice' field.
   * @return The value of the 'calculationPrice' field.
   */
  public java.lang.CharSequence getCalculationPrice() {
    return calculationPrice;
  }

  /**
   * Sets the value of the 'calculationPrice' field.
   * @param value the value to set.
   */
  public void setCalculationPrice(java.lang.CharSequence value) {
    this.calculationPrice = value;
  }

  /**
   * Gets the value of the 'open' field.
   * @return The value of the 'open' field.
   */
  public java.lang.Double getOpen() {
    return open;
  }

  /**
   * Sets the value of the 'open' field.
   * @param value the value to set.
   */
  public void setOpen(java.lang.Double value) {
    this.open = value;
  }

  /**
   * Gets the value of the 'openTime' field.
   * @return The value of the 'openTime' field.
   */
  public java.lang.Long getOpenTime() {
    return openTime;
  }

  /**
   * Sets the value of the 'openTime' field.
   * @param value the value to set.
   */
  public void setOpenTime(java.lang.Long value) {
    this.openTime = value;
  }

  /**
   * Gets the value of the 'close' field.
   * @return The value of the 'close' field.
   */
  public java.lang.Double getClose() {
    return close;
  }

  /**
   * Sets the value of the 'close' field.
   * @param value the value to set.
   */
  public void setClose(java.lang.Double value) {
    this.close = value;
  }

  /**
   * Gets the value of the 'closeTime' field.
   * @return The value of the 'closeTime' field.
   */
  public java.lang.Long getCloseTime() {
    return closeTime;
  }

  /**
   * Sets the value of the 'closeTime' field.
   * @param value the value to set.
   */
  public void setCloseTime(java.lang.Long value) {
    this.closeTime = value;
  }

  /**
   * Gets the value of the 'high' field.
   * @return The value of the 'high' field.
   */
  public java.lang.Double getHigh() {
    return high;
  }

  /**
   * Sets the value of the 'high' field.
   * @param value the value to set.
   */
  public void setHigh(java.lang.Double value) {
    this.high = value;
  }

  /**
   * Gets the value of the 'low' field.
   * @return The value of the 'low' field.
   */
  public java.lang.Double getLow() {
    return low;
  }

  /**
   * Sets the value of the 'low' field.
   * @param value the value to set.
   */
  public void setLow(java.lang.Double value) {
    this.low = value;
  }

  /**
   * Gets the value of the 'latestPrice' field.
   * @return The value of the 'latestPrice' field.
   */
  public java.lang.Double getLatestPrice() {
    return latestPrice;
  }

  /**
   * Sets the value of the 'latestPrice' field.
   * @param value the value to set.
   */
  public void setLatestPrice(java.lang.Double value) {
    this.latestPrice = value;
  }

  /**
   * Creates a new IexTrading RecordBuilder.
   * @return A new IexTrading RecordBuilder
   */
  public static com.example.avro.IexTrading.Builder newBuilder() {
    return new com.example.avro.IexTrading.Builder();
  }

  /**
   * Creates a new IexTrading RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new IexTrading RecordBuilder
   */
  public static com.example.avro.IexTrading.Builder newBuilder(com.example.avro.IexTrading.Builder other) {
    return new com.example.avro.IexTrading.Builder(other);
  }

  /**
   * Creates a new IexTrading RecordBuilder by copying an existing IexTrading instance.
   * @param other The existing instance to copy.
   * @return A new IexTrading RecordBuilder
   */
  public static com.example.avro.IexTrading.Builder newBuilder(com.example.avro.IexTrading other) {
    return new com.example.avro.IexTrading.Builder(other);
  }

  /**
   * RecordBuilder for IexTrading instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<IexTrading>
    implements org.apache.avro.data.RecordBuilder<IexTrading> {

    /** Stock Symbol */
    private java.lang.CharSequence symbol;
    /** Company Name */
    private java.lang.CharSequence companyName;
    private java.lang.CharSequence primaryExchange;
    private java.lang.CharSequence sector;
    private java.lang.CharSequence calculationPrice;
    private java.lang.Double open;
    private java.lang.Long openTime;
    private java.lang.Double close;
    private java.lang.Long closeTime;
    private java.lang.Double high;
    private java.lang.Double low;
    private java.lang.Double latestPrice;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.example.avro.IexTrading.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.symbol)) {
        this.symbol = data().deepCopy(fields()[0].schema(), other.symbol);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.companyName)) {
        this.companyName = data().deepCopy(fields()[1].schema(), other.companyName);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.primaryExchange)) {
        this.primaryExchange = data().deepCopy(fields()[2].schema(), other.primaryExchange);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.sector)) {
        this.sector = data().deepCopy(fields()[3].schema(), other.sector);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.calculationPrice)) {
        this.calculationPrice = data().deepCopy(fields()[4].schema(), other.calculationPrice);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.open)) {
        this.open = data().deepCopy(fields()[5].schema(), other.open);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.openTime)) {
        this.openTime = data().deepCopy(fields()[6].schema(), other.openTime);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.close)) {
        this.close = data().deepCopy(fields()[7].schema(), other.close);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.closeTime)) {
        this.closeTime = data().deepCopy(fields()[8].schema(), other.closeTime);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.high)) {
        this.high = data().deepCopy(fields()[9].schema(), other.high);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.low)) {
        this.low = data().deepCopy(fields()[10].schema(), other.low);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other.latestPrice)) {
        this.latestPrice = data().deepCopy(fields()[11].schema(), other.latestPrice);
        fieldSetFlags()[11] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing IexTrading instance
     * @param other The existing instance to copy.
     */
    private Builder(com.example.avro.IexTrading other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.symbol)) {
        this.symbol = data().deepCopy(fields()[0].schema(), other.symbol);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.companyName)) {
        this.companyName = data().deepCopy(fields()[1].schema(), other.companyName);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.primaryExchange)) {
        this.primaryExchange = data().deepCopy(fields()[2].schema(), other.primaryExchange);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.sector)) {
        this.sector = data().deepCopy(fields()[3].schema(), other.sector);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.calculationPrice)) {
        this.calculationPrice = data().deepCopy(fields()[4].schema(), other.calculationPrice);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.open)) {
        this.open = data().deepCopy(fields()[5].schema(), other.open);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.openTime)) {
        this.openTime = data().deepCopy(fields()[6].schema(), other.openTime);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.close)) {
        this.close = data().deepCopy(fields()[7].schema(), other.close);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.closeTime)) {
        this.closeTime = data().deepCopy(fields()[8].schema(), other.closeTime);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.high)) {
        this.high = data().deepCopy(fields()[9].schema(), other.high);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.low)) {
        this.low = data().deepCopy(fields()[10].schema(), other.low);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other.latestPrice)) {
        this.latestPrice = data().deepCopy(fields()[11].schema(), other.latestPrice);
        fieldSetFlags()[11] = true;
      }
    }

    /**
      * Gets the value of the 'symbol' field.
      * Stock Symbol
      * @return The value.
      */
    public java.lang.CharSequence getSymbol() {
      return symbol;
    }

    /**
      * Sets the value of the 'symbol' field.
      * Stock Symbol
      * @param value The value of 'symbol'.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder setSymbol(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.symbol = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'symbol' field has been set.
      * Stock Symbol
      * @return True if the 'symbol' field has been set, false otherwise.
      */
    public boolean hasSymbol() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'symbol' field.
      * Stock Symbol
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder clearSymbol() {
      symbol = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'companyName' field.
      * Company Name
      * @return The value.
      */
    public java.lang.CharSequence getCompanyName() {
      return companyName;
    }

    /**
      * Sets the value of the 'companyName' field.
      * Company Name
      * @param value The value of 'companyName'.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder setCompanyName(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.companyName = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'companyName' field has been set.
      * Company Name
      * @return True if the 'companyName' field has been set, false otherwise.
      */
    public boolean hasCompanyName() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'companyName' field.
      * Company Name
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder clearCompanyName() {
      companyName = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'primaryExchange' field.
      * @return The value.
      */
    public java.lang.CharSequence getPrimaryExchange() {
      return primaryExchange;
    }

    /**
      * Sets the value of the 'primaryExchange' field.
      * @param value The value of 'primaryExchange'.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder setPrimaryExchange(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.primaryExchange = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'primaryExchange' field has been set.
      * @return True if the 'primaryExchange' field has been set, false otherwise.
      */
    public boolean hasPrimaryExchange() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'primaryExchange' field.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder clearPrimaryExchange() {
      primaryExchange = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'sector' field.
      * @return The value.
      */
    public java.lang.CharSequence getSector() {
      return sector;
    }

    /**
      * Sets the value of the 'sector' field.
      * @param value The value of 'sector'.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder setSector(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.sector = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'sector' field has been set.
      * @return True if the 'sector' field has been set, false otherwise.
      */
    public boolean hasSector() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'sector' field.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder clearSector() {
      sector = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'calculationPrice' field.
      * @return The value.
      */
    public java.lang.CharSequence getCalculationPrice() {
      return calculationPrice;
    }

    /**
      * Sets the value of the 'calculationPrice' field.
      * @param value The value of 'calculationPrice'.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder setCalculationPrice(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.calculationPrice = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'calculationPrice' field has been set.
      * @return True if the 'calculationPrice' field has been set, false otherwise.
      */
    public boolean hasCalculationPrice() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'calculationPrice' field.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder clearCalculationPrice() {
      calculationPrice = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'open' field.
      * @return The value.
      */
    public java.lang.Double getOpen() {
      return open;
    }

    /**
      * Sets the value of the 'open' field.
      * @param value The value of 'open'.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder setOpen(java.lang.Double value) {
      validate(fields()[5], value);
      this.open = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'open' field has been set.
      * @return True if the 'open' field has been set, false otherwise.
      */
    public boolean hasOpen() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'open' field.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder clearOpen() {
      open = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'openTime' field.
      * @return The value.
      */
    public java.lang.Long getOpenTime() {
      return openTime;
    }

    /**
      * Sets the value of the 'openTime' field.
      * @param value The value of 'openTime'.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder setOpenTime(java.lang.Long value) {
      validate(fields()[6], value);
      this.openTime = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'openTime' field has been set.
      * @return True if the 'openTime' field has been set, false otherwise.
      */
    public boolean hasOpenTime() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'openTime' field.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder clearOpenTime() {
      openTime = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'close' field.
      * @return The value.
      */
    public java.lang.Double getClose() {
      return close;
    }

    /**
      * Sets the value of the 'close' field.
      * @param value The value of 'close'.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder setClose(java.lang.Double value) {
      validate(fields()[7], value);
      this.close = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'close' field has been set.
      * @return True if the 'close' field has been set, false otherwise.
      */
    public boolean hasClose() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'close' field.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder clearClose() {
      close = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'closeTime' field.
      * @return The value.
      */
    public java.lang.Long getCloseTime() {
      return closeTime;
    }

    /**
      * Sets the value of the 'closeTime' field.
      * @param value The value of 'closeTime'.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder setCloseTime(java.lang.Long value) {
      validate(fields()[8], value);
      this.closeTime = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'closeTime' field has been set.
      * @return True if the 'closeTime' field has been set, false otherwise.
      */
    public boolean hasCloseTime() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'closeTime' field.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder clearCloseTime() {
      closeTime = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the 'high' field.
      * @return The value.
      */
    public java.lang.Double getHigh() {
      return high;
    }

    /**
      * Sets the value of the 'high' field.
      * @param value The value of 'high'.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder setHigh(java.lang.Double value) {
      validate(fields()[9], value);
      this.high = value;
      fieldSetFlags()[9] = true;
      return this;
    }

    /**
      * Checks whether the 'high' field has been set.
      * @return True if the 'high' field has been set, false otherwise.
      */
    public boolean hasHigh() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the 'high' field.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder clearHigh() {
      high = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /**
      * Gets the value of the 'low' field.
      * @return The value.
      */
    public java.lang.Double getLow() {
      return low;
    }

    /**
      * Sets the value of the 'low' field.
      * @param value The value of 'low'.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder setLow(java.lang.Double value) {
      validate(fields()[10], value);
      this.low = value;
      fieldSetFlags()[10] = true;
      return this;
    }

    /**
      * Checks whether the 'low' field has been set.
      * @return True if the 'low' field has been set, false otherwise.
      */
    public boolean hasLow() {
      return fieldSetFlags()[10];
    }


    /**
      * Clears the value of the 'low' field.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder clearLow() {
      low = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    /**
      * Gets the value of the 'latestPrice' field.
      * @return The value.
      */
    public java.lang.Double getLatestPrice() {
      return latestPrice;
    }

    /**
      * Sets the value of the 'latestPrice' field.
      * @param value The value of 'latestPrice'.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder setLatestPrice(java.lang.Double value) {
      validate(fields()[11], value);
      this.latestPrice = value;
      fieldSetFlags()[11] = true;
      return this;
    }

    /**
      * Checks whether the 'latestPrice' field has been set.
      * @return True if the 'latestPrice' field has been set, false otherwise.
      */
    public boolean hasLatestPrice() {
      return fieldSetFlags()[11];
    }


    /**
      * Clears the value of the 'latestPrice' field.
      * @return This builder.
      */
    public com.example.avro.IexTrading.Builder clearLatestPrice() {
      latestPrice = null;
      fieldSetFlags()[11] = false;
      return this;
    }

    @Override
    public IexTrading build() {
      try {
        IexTrading record = new IexTrading();
        record.symbol = fieldSetFlags()[0] ? this.symbol : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.companyName = fieldSetFlags()[1] ? this.companyName : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.primaryExchange = fieldSetFlags()[2] ? this.primaryExchange : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.sector = fieldSetFlags()[3] ? this.sector : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.calculationPrice = fieldSetFlags()[4] ? this.calculationPrice : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.open = fieldSetFlags()[5] ? this.open : (java.lang.Double) defaultValue(fields()[5]);
        record.openTime = fieldSetFlags()[6] ? this.openTime : (java.lang.Long) defaultValue(fields()[6]);
        record.close = fieldSetFlags()[7] ? this.close : (java.lang.Double) defaultValue(fields()[7]);
        record.closeTime = fieldSetFlags()[8] ? this.closeTime : (java.lang.Long) defaultValue(fields()[8]);
        record.high = fieldSetFlags()[9] ? this.high : (java.lang.Double) defaultValue(fields()[9]);
        record.low = fieldSetFlags()[10] ? this.low : (java.lang.Double) defaultValue(fields()[10]);
        record.latestPrice = fieldSetFlags()[11] ? this.latestPrice : (java.lang.Double) defaultValue(fields()[11]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}