package tech.harry.online_book_strore.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;

@Entity
@Data
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="file_name")
    private String fileName;

    @Column(name = "file_type")
    private  String fileType;

    @Lob
    @Column(name = "image")
    private Blob image;

    @Column(name = "download_url")
    private String downloadUrl;

    @ManyToOne()
    @JoinColumn(name = "book_id")
    private Books books;

}
