## lucene tutorial

This is tutorial/sandbox repository of Apache Lucene.

## How to run?

```bash
mvn exec:java
```

## What doing?

### Indexing

Index 2 documents:

| doc | title | body |
| ---- | ---- | ---- |
| 1 | hello lucene| lucene is awesome search engine! |
| 2 | hello solr | solr is great search server! |

### Search

Search query is `lucene`. So, the query matches only the first one.

### LICENSE

MIT
