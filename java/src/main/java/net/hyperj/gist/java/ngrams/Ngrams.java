package net.hyperj.gist.java.ngrams;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Ngrams {

    private static final Map<Integer, DirectoryReader> READER_MAP = new HashMap<Integer, DirectoryReader>() {
        {
            put(1, getReader(1));
            put(2, getReader(2));
            put(3, getReader(3));
        }

        private DirectoryReader getReader(int n) {
            try {
                Path path = new File(Ngrams.class.getClassLoader().getResource("ngrams/" + n + "grams").getPath()).toPath();
                FSDirectory dir = FSDirectory.open(path);
                return DirectoryReader.open(dir);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    private static final String FIELD_NAME = "ngram";
    private static final String FIELD_VALUE = "count";
    private static final String BLANK = " ";
    private static final String ZERO = "0";

    public static String getCount(String... strs) {
        if (strs != null && strs.length >= 1 && strs.length <= 3) {
            DirectoryReader reader = READER_MAP.get(strs.length);
            if (reader != null) {
                IndexSearcher searcher = new IndexSearcher(reader);
                RegexpQuery query = new RegexpQuery(new Term(FIELD_NAME, String.join(BLANK, strs)));
                try {
                    TopDocs docs = searcher.search(query, 1);
                    for (ScoreDoc scoreDoc : docs.scoreDocs) {
                        try {
                            return reader.document(scoreDoc.doc).get(FIELD_VALUE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ZERO;
    }

    public static void main(String[] args) {
        System.out.printf(getCount("hello", "world"));
    }

}
