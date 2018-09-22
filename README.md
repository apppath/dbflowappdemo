# DBFlow
DBFlow is a SQLite library for Android that makes it ridiculously easy to interact and use databases. Built with Annotation Processing that generates most of the boilerplate code for you, code use within a DB is fast, efficient, and type-safe. It removes the tedious (and tough-to-maintain) database interaction code.

## Gradle Dependencies

```gradle

allProjects {
  repositories {
    // required to find the project's artifacts
    maven { url "https://www.jitpack.io" }
  }
}

def dbflow_version = "4.2.4"

annotationProcessor "com.github.Raizlabs.DBFlow:dbflow-processor:${dbflow_version}"
implementation "com.github.Raizlabs.DBFlow:dbflow-core:${dbflow_version}"
implementation "com.github.Raizlabs.DBFlow:dbflow:${dbflow_version}"

```

## DBFlow Configuration
* To ensure generated code in DBFlow is found by the library, initialize the library in your Application class:

```java

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}

```

## Create Database 
* Creating a database is as easy as a few lines of code:

```java

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "bookDatabase";
    public static final int VERSION = 2;

}

```

## Create Table
* Creating a table is also very simple:

```java 

import com.example.basicprogramming.dbflowappdemo.dbconfig.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(name = "booksTable", database = AppDatabase.class)
public class Book extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private Long id;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "book_price")
    private Double bookPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }
}

```

## App Images

<p align="center">
  <img src="https://github.com/apppath/dbflowappdemo/blob/master/Screenshot_1537522723.png" width="400"/>
  <img src="https://github.com/apppath/dbflowappdemo/blob/master/Screenshot_1537522751.png" width="400"/>
</p>


<p align="center">
  <img src="https://github.com/apppath/dbflowappdemo/blob/master/Screenshot_1537522742.png" width="400"/>
  <img src="https://github.com/apppath/dbflowappdemo/blob/master/Screenshot_1537522735.png" width="400"/>
</p>

# Done Work
