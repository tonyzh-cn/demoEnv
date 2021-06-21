import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class FirstLucene {
    @Test
    public void testIndex() throws IOException {
        Directory directory = FSDirectory.open(new File("D:\\Tmp\\index"));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,analyzer);
        IndexWriter indexWriter = new IndexWriter(directory,config);

        File resources = new File("D:\\Tmp\\resources");
        for(File resource : resources.listFiles()){
            Document document = new Document();
            Field fileNameField = new TextField("fileName",resource.getName(), Field.Store.YES);
            Field fileSizeField = new LongField("fileSize",FileUtils.sizeOf(resource), Field.Store.YES);
            Field filePathField = new StoredField("filePath",resource.getPath());
            Field fileContentField = new TextField("fileContent", FileUtils.readFileToString(resource), Field.Store.YES);
            document.add(fileNameField);
            document.add(fileSizeField);
            document.add(filePathField);
            document.add(fileContentField);
            indexWriter.addDocument(document);
        }

        indexWriter.close();
    }

    @Test
    public void testSearch() throws IOException {
        Directory directory = FSDirectory.open(new File("D:\\Tmp\\index"));
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        Query query = new TermQuery(new Term("fileName","java.txt"));
        TopDocs topDocs = indexSearcher.search(query,2);
        ScoreDoc []scoreDocs = topDocs.scoreDocs;
        for(ScoreDoc scoreDoc : scoreDocs){
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            System.out.println(document.get("fileName"));
            System.out.println(document.get("fileSize"));
            System.out.println(document.get("filePath"));
            System.out.println(document.get("fileContent"));
        }

        indexReader.close();
    }

    @Test
    public void testTokenStream() throws Exception {
        // 创建一个标准分析器对象
//		Analyzer analyzer = new StandardAnalyzer();
//		Analyzer analyzer = new CJKAnalyzer();
//		Analyzer analyzer = new SmartChineseAnalyzer();
        Analyzer analyzer = new IKAnalyzer();
        // 获得tokenStream对象
        // 第一个参数：域名，可以随便给一个
        // 第二个参数：要分析的文本内容
//		TokenStream tokenStream = analyzer.tokenStream("test",
//				"The Spring Framework provides a comprehensive programming and configuration model.");
        TokenStream tokenStream = analyzer.tokenStream("test",
                "高富帅可以用二维表结构来逻辑表达实现的数据");
        // 添加一个引用，可以获得每个关键词
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        // 添加一个偏移量的引用，记录了关键词的开始位置以及结束位置
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        // 将指针调整到列表的头部
        tokenStream.reset();
        // 遍历关键词列表，通过incrementToken方法判断列表是否结束
        while (tokenStream.incrementToken()) {
            // 关键词的起始位置
            System.out.println("start->" + offsetAttribute.startOffset());
            // 取关键词
            System.out.println(charTermAttribute);
            // 结束位置
            System.out.println("end->" + offsetAttribute.endOffset());
        }
        tokenStream.close();
    }
}
