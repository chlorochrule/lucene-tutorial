package com.chlorochrule.sandbox.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        String workspace = "src/main/resources/workspace";

        FileUtils.cleanDirectory(new File(workspace));

        Directory directory = FSDirectory.open(Paths.get(workspace));
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(directory, config);

        Document document = new Document();
        document.add(new TextField("title", "hello lucene", TextField.Store.YES));
        document.add(new TextField("body", "lucene is awesome search engine!", TextField.Store.YES));
        writer.addDocument(document);

        document = new Document();
        document.add(new TextField("title", "hello solr", TextField.Store.YES));
        document.add(new TextField("body", "solr is great search server!", Field.Store.YES));
        writer.addDocument(document);

        writer.close();

        IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(writer.getDirectory()));
        QueryParser parser = new QueryParser("body", new StandardAnalyzer());
        Query query = parser.parse("lucene");
        for (ScoreDoc doc : searcher.search(query, 10).scoreDocs) {
            System.out.println(searcher.doc(doc.doc).get("title"));
        }
    }
}
