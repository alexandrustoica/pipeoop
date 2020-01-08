// ORM class for table 'workunit'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jan 08 02:33:11 PST 2020
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class workunit extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("part_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        part_id = (Integer)value;
      }
    });
    setters.put("batch_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        batch_id = (Integer)value;
      }
    });
    setters.put("timestamp", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        timestamp = (java.sql.Timestamp)value;
      }
    });
    setters.put("tool_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        tool_id = (String)value;
      }
    });
    setters.put("cup_pressure", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        cup_pressure = (Double)value;
      }
    });
    setters.put("lower_cup_pressure", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        lower_cup_pressure = (Double)value;
      }
    });
    setters.put("upper_cup_pressure", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        upper_cup_pressure = (Double)value;
      }
    });
    setters.put("air_pressure", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        air_pressure = (Double)value;
      }
    });
    setters.put("lower_air_pressure", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        lower_air_pressure = (Double)value;
      }
    });
    setters.put("upper_air_pressure", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        upper_air_pressure = (Double)value;
      }
    });
  }
  public workunit() {
    init0();
  }
  private Integer part_id;
  public Integer get_part_id() {
    return part_id;
  }
  public void set_part_id(Integer part_id) {
    this.part_id = part_id;
  }
  public workunit with_part_id(Integer part_id) {
    this.part_id = part_id;
    return this;
  }
  private Integer batch_id;
  public Integer get_batch_id() {
    return batch_id;
  }
  public void set_batch_id(Integer batch_id) {
    this.batch_id = batch_id;
  }
  public workunit with_batch_id(Integer batch_id) {
    this.batch_id = batch_id;
    return this;
  }
  private java.sql.Timestamp timestamp;
  public java.sql.Timestamp get_timestamp() {
    return timestamp;
  }
  public void set_timestamp(java.sql.Timestamp timestamp) {
    this.timestamp = timestamp;
  }
  public workunit with_timestamp(java.sql.Timestamp timestamp) {
    this.timestamp = timestamp;
    return this;
  }
  private String tool_id;
  public String get_tool_id() {
    return tool_id;
  }
  public void set_tool_id(String tool_id) {
    this.tool_id = tool_id;
  }
  public workunit with_tool_id(String tool_id) {
    this.tool_id = tool_id;
    return this;
  }
  private Double cup_pressure;
  public Double get_cup_pressure() {
    return cup_pressure;
  }
  public void set_cup_pressure(Double cup_pressure) {
    this.cup_pressure = cup_pressure;
  }
  public workunit with_cup_pressure(Double cup_pressure) {
    this.cup_pressure = cup_pressure;
    return this;
  }
  private Double lower_cup_pressure;
  public Double get_lower_cup_pressure() {
    return lower_cup_pressure;
  }
  public void set_lower_cup_pressure(Double lower_cup_pressure) {
    this.lower_cup_pressure = lower_cup_pressure;
  }
  public workunit with_lower_cup_pressure(Double lower_cup_pressure) {
    this.lower_cup_pressure = lower_cup_pressure;
    return this;
  }
  private Double upper_cup_pressure;
  public Double get_upper_cup_pressure() {
    return upper_cup_pressure;
  }
  public void set_upper_cup_pressure(Double upper_cup_pressure) {
    this.upper_cup_pressure = upper_cup_pressure;
  }
  public workunit with_upper_cup_pressure(Double upper_cup_pressure) {
    this.upper_cup_pressure = upper_cup_pressure;
    return this;
  }
  private Double air_pressure;
  public Double get_air_pressure() {
    return air_pressure;
  }
  public void set_air_pressure(Double air_pressure) {
    this.air_pressure = air_pressure;
  }
  public workunit with_air_pressure(Double air_pressure) {
    this.air_pressure = air_pressure;
    return this;
  }
  private Double lower_air_pressure;
  public Double get_lower_air_pressure() {
    return lower_air_pressure;
  }
  public void set_lower_air_pressure(Double lower_air_pressure) {
    this.lower_air_pressure = lower_air_pressure;
  }
  public workunit with_lower_air_pressure(Double lower_air_pressure) {
    this.lower_air_pressure = lower_air_pressure;
    return this;
  }
  private Double upper_air_pressure;
  public Double get_upper_air_pressure() {
    return upper_air_pressure;
  }
  public void set_upper_air_pressure(Double upper_air_pressure) {
    this.upper_air_pressure = upper_air_pressure;
  }
  public workunit with_upper_air_pressure(Double upper_air_pressure) {
    this.upper_air_pressure = upper_air_pressure;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof workunit)) {
      return false;
    }
    workunit that = (workunit) o;
    boolean equal = true;
    equal = equal && (this.part_id == null ? that.part_id == null : this.part_id.equals(that.part_id));
    equal = equal && (this.batch_id == null ? that.batch_id == null : this.batch_id.equals(that.batch_id));
    equal = equal && (this.timestamp == null ? that.timestamp == null : this.timestamp.equals(that.timestamp));
    equal = equal && (this.tool_id == null ? that.tool_id == null : this.tool_id.equals(that.tool_id));
    equal = equal && (this.cup_pressure == null ? that.cup_pressure == null : this.cup_pressure.equals(that.cup_pressure));
    equal = equal && (this.lower_cup_pressure == null ? that.lower_cup_pressure == null : this.lower_cup_pressure.equals(that.lower_cup_pressure));
    equal = equal && (this.upper_cup_pressure == null ? that.upper_cup_pressure == null : this.upper_cup_pressure.equals(that.upper_cup_pressure));
    equal = equal && (this.air_pressure == null ? that.air_pressure == null : this.air_pressure.equals(that.air_pressure));
    equal = equal && (this.lower_air_pressure == null ? that.lower_air_pressure == null : this.lower_air_pressure.equals(that.lower_air_pressure));
    equal = equal && (this.upper_air_pressure == null ? that.upper_air_pressure == null : this.upper_air_pressure.equals(that.upper_air_pressure));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof workunit)) {
      return false;
    }
    workunit that = (workunit) o;
    boolean equal = true;
    equal = equal && (this.part_id == null ? that.part_id == null : this.part_id.equals(that.part_id));
    equal = equal && (this.batch_id == null ? that.batch_id == null : this.batch_id.equals(that.batch_id));
    equal = equal && (this.timestamp == null ? that.timestamp == null : this.timestamp.equals(that.timestamp));
    equal = equal && (this.tool_id == null ? that.tool_id == null : this.tool_id.equals(that.tool_id));
    equal = equal && (this.cup_pressure == null ? that.cup_pressure == null : this.cup_pressure.equals(that.cup_pressure));
    equal = equal && (this.lower_cup_pressure == null ? that.lower_cup_pressure == null : this.lower_cup_pressure.equals(that.lower_cup_pressure));
    equal = equal && (this.upper_cup_pressure == null ? that.upper_cup_pressure == null : this.upper_cup_pressure.equals(that.upper_cup_pressure));
    equal = equal && (this.air_pressure == null ? that.air_pressure == null : this.air_pressure.equals(that.air_pressure));
    equal = equal && (this.lower_air_pressure == null ? that.lower_air_pressure == null : this.lower_air_pressure.equals(that.lower_air_pressure));
    equal = equal && (this.upper_air_pressure == null ? that.upper_air_pressure == null : this.upper_air_pressure.equals(that.upper_air_pressure));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.part_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.batch_id = JdbcWritableBridge.readInteger(2, __dbResults);
    this.timestamp = JdbcWritableBridge.readTimestamp(3, __dbResults);
    this.tool_id = JdbcWritableBridge.readString(4, __dbResults);
    this.cup_pressure = JdbcWritableBridge.readDouble(5, __dbResults);
    this.lower_cup_pressure = JdbcWritableBridge.readDouble(6, __dbResults);
    this.upper_cup_pressure = JdbcWritableBridge.readDouble(7, __dbResults);
    this.air_pressure = JdbcWritableBridge.readDouble(8, __dbResults);
    this.lower_air_pressure = JdbcWritableBridge.readDouble(9, __dbResults);
    this.upper_air_pressure = JdbcWritableBridge.readDouble(10, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.part_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.batch_id = JdbcWritableBridge.readInteger(2, __dbResults);
    this.timestamp = JdbcWritableBridge.readTimestamp(3, __dbResults);
    this.tool_id = JdbcWritableBridge.readString(4, __dbResults);
    this.cup_pressure = JdbcWritableBridge.readDouble(5, __dbResults);
    this.lower_cup_pressure = JdbcWritableBridge.readDouble(6, __dbResults);
    this.upper_cup_pressure = JdbcWritableBridge.readDouble(7, __dbResults);
    this.air_pressure = JdbcWritableBridge.readDouble(8, __dbResults);
    this.lower_air_pressure = JdbcWritableBridge.readDouble(9, __dbResults);
    this.upper_air_pressure = JdbcWritableBridge.readDouble(10, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(part_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(batch_id, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeTimestamp(timestamp, 3 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeString(tool_id, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDouble(cup_pressure, 5 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(lower_cup_pressure, 6 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(upper_cup_pressure, 7 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(air_pressure, 8 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(lower_air_pressure, 9 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(upper_air_pressure, 10 + __off, 8, __dbStmt);
    return 10;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(part_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(batch_id, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeTimestamp(timestamp, 3 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeString(tool_id, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeDouble(cup_pressure, 5 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(lower_cup_pressure, 6 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(upper_cup_pressure, 7 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(air_pressure, 8 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(lower_air_pressure, 9 + __off, 8, __dbStmt);
    JdbcWritableBridge.writeDouble(upper_air_pressure, 10 + __off, 8, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.part_id = null;
    } else {
    this.part_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.batch_id = null;
    } else {
    this.batch_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.timestamp = null;
    } else {
    this.timestamp = new Timestamp(__dataIn.readLong());
    this.timestamp.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.tool_id = null;
    } else {
    this.tool_id = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.cup_pressure = null;
    } else {
    this.cup_pressure = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.lower_cup_pressure = null;
    } else {
    this.lower_cup_pressure = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.upper_cup_pressure = null;
    } else {
    this.upper_cup_pressure = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.air_pressure = null;
    } else {
    this.air_pressure = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.lower_air_pressure = null;
    } else {
    this.lower_air_pressure = Double.valueOf(__dataIn.readDouble());
    }
    if (__dataIn.readBoolean()) { 
        this.upper_air_pressure = null;
    } else {
    this.upper_air_pressure = Double.valueOf(__dataIn.readDouble());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.part_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.part_id);
    }
    if (null == this.batch_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.batch_id);
    }
    if (null == this.timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.timestamp.getTime());
    __dataOut.writeInt(this.timestamp.getNanos());
    }
    if (null == this.tool_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, tool_id);
    }
    if (null == this.cup_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.cup_pressure);
    }
    if (null == this.lower_cup_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.lower_cup_pressure);
    }
    if (null == this.upper_cup_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.upper_cup_pressure);
    }
    if (null == this.air_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.air_pressure);
    }
    if (null == this.lower_air_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.lower_air_pressure);
    }
    if (null == this.upper_air_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.upper_air_pressure);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.part_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.part_id);
    }
    if (null == this.batch_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.batch_id);
    }
    if (null == this.timestamp) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.timestamp.getTime());
    __dataOut.writeInt(this.timestamp.getNanos());
    }
    if (null == this.tool_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, tool_id);
    }
    if (null == this.cup_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.cup_pressure);
    }
    if (null == this.lower_cup_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.lower_cup_pressure);
    }
    if (null == this.upper_cup_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.upper_cup_pressure);
    }
    if (null == this.air_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.air_pressure);
    }
    if (null == this.lower_air_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.lower_air_pressure);
    }
    if (null == this.upper_air_pressure) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeDouble(this.upper_air_pressure);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(part_id==null?"null":"" + part_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(batch_id==null?"null":"" + batch_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(timestamp==null?"null":"" + timestamp, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tool_id==null?"null":tool_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cup_pressure==null?"null":"" + cup_pressure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lower_cup_pressure==null?"null":"" + lower_cup_pressure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(upper_cup_pressure==null?"null":"" + upper_cup_pressure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(air_pressure==null?"null":"" + air_pressure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lower_air_pressure==null?"null":"" + lower_air_pressure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(upper_air_pressure==null?"null":"" + upper_air_pressure, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(part_id==null?"null":"" + part_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(batch_id==null?"null":"" + batch_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(timestamp==null?"null":"" + timestamp, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tool_id==null?"null":tool_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cup_pressure==null?"null":"" + cup_pressure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lower_cup_pressure==null?"null":"" + lower_cup_pressure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(upper_cup_pressure==null?"null":"" + upper_cup_pressure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(air_pressure==null?"null":"" + air_pressure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lower_air_pressure==null?"null":"" + lower_air_pressure, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(upper_air_pressure==null?"null":"" + upper_air_pressure, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.part_id = null; } else {
      this.part_id = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.batch_id = null; } else {
      this.batch_id = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.timestamp = null; } else {
      this.timestamp = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.tool_id = null; } else {
      this.tool_id = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cup_pressure = null; } else {
      this.cup_pressure = Double.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lower_cup_pressure = null; } else {
      this.lower_cup_pressure = Double.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.upper_cup_pressure = null; } else {
      this.upper_cup_pressure = Double.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.air_pressure = null; } else {
      this.air_pressure = Double.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lower_air_pressure = null; } else {
      this.lower_air_pressure = Double.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.upper_air_pressure = null; } else {
      this.upper_air_pressure = Double.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.part_id = null; } else {
      this.part_id = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.batch_id = null; } else {
      this.batch_id = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.timestamp = null; } else {
      this.timestamp = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.tool_id = null; } else {
      this.tool_id = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cup_pressure = null; } else {
      this.cup_pressure = Double.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lower_cup_pressure = null; } else {
      this.lower_cup_pressure = Double.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.upper_cup_pressure = null; } else {
      this.upper_cup_pressure = Double.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.air_pressure = null; } else {
      this.air_pressure = Double.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lower_air_pressure = null; } else {
      this.lower_air_pressure = Double.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.upper_air_pressure = null; } else {
      this.upper_air_pressure = Double.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    workunit o = (workunit) super.clone();
    o.timestamp = (o.timestamp != null) ? (java.sql.Timestamp) o.timestamp.clone() : null;
    return o;
  }

  public void clone0(workunit o) throws CloneNotSupportedException {
    o.timestamp = (o.timestamp != null) ? (java.sql.Timestamp) o.timestamp.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("part_id", this.part_id);
    __sqoop$field_map.put("batch_id", this.batch_id);
    __sqoop$field_map.put("timestamp", this.timestamp);
    __sqoop$field_map.put("tool_id", this.tool_id);
    __sqoop$field_map.put("cup_pressure", this.cup_pressure);
    __sqoop$field_map.put("lower_cup_pressure", this.lower_cup_pressure);
    __sqoop$field_map.put("upper_cup_pressure", this.upper_cup_pressure);
    __sqoop$field_map.put("air_pressure", this.air_pressure);
    __sqoop$field_map.put("lower_air_pressure", this.lower_air_pressure);
    __sqoop$field_map.put("upper_air_pressure", this.upper_air_pressure);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("part_id", this.part_id);
    __sqoop$field_map.put("batch_id", this.batch_id);
    __sqoop$field_map.put("timestamp", this.timestamp);
    __sqoop$field_map.put("tool_id", this.tool_id);
    __sqoop$field_map.put("cup_pressure", this.cup_pressure);
    __sqoop$field_map.put("lower_cup_pressure", this.lower_cup_pressure);
    __sqoop$field_map.put("upper_cup_pressure", this.upper_cup_pressure);
    __sqoop$field_map.put("air_pressure", this.air_pressure);
    __sqoop$field_map.put("lower_air_pressure", this.lower_air_pressure);
    __sqoop$field_map.put("upper_air_pressure", this.upper_air_pressure);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
