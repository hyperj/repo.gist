package net.hyperj.gist.orc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.orc.OrcFile;
import org.apache.orc.Reader;
import org.apache.orc.RecordReader;
import org.apache.orc.TypeDescription;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;

import java.io.IOException;

import static net.hyperj.gist.common.dict.StringDict.*;
import static net.hyperj.gist.common.kit.StringKit.*;

public class CoreReader {
    public static void main(String[] args) throws IOException {
        Reader reader = OrcFile.createReader(new Path("file.orc"),
                OrcFile.readerOptions(new Configuration()));
        printf("File schema: " + reader.getSchema());
        printf("Row count: " + reader.getNumberOfRows());
        TypeDescription readSchema =
                TypeDescription.fromString("struct<z:int,y:string,x:bigint>");
        VectorizedRowBatch batch = readSchema.createRowBatch();
        RecordReader rowIterator = reader.rows(reader.options()
                .schema(readSchema));
        LongColumnVector z = (LongColumnVector) batch.cols[0];
        BytesColumnVector y = (BytesColumnVector) batch.cols[1];
        LongColumnVector x = (LongColumnVector) batch.cols[2];
        while (rowIterator.nextBatch(batch)) {
            for (int row = 0; row < batch.size; ++row) {
                int zRow = z.isRepeating ? 0 : row;
                int xRow = x.isRepeating ? 0 : row;
                print(new StringBuilder("raw: ")
                        .append("x=" + (x.noNulls || !x.isNull[xRow] ? x.vector[xRow] : null)).append(SEMICOLON)
                        .append("y=" + y.toString(row)).append(SEMICOLON)
                        .append("z=" + (z.noNulls || !z.isNull[zRow] ? z.vector[zRow] : null)).append(LINE_BREAK)
                        .toString());
            }
        }
        rowIterator.close();
    }
}
