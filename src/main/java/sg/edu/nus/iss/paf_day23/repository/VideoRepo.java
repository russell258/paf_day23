package sg.edu.nus.iss.paf_day23.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day23.model.Video;

@Repository
public class VideoRepo {
    @Autowired
    JdbcTemplate jdbc;

    private String findAllVideoSQL = "select * from video";

    private String insertVideoSQL = "insert into video (title, synopsis, available_count) values (?,?,?)";

    private String updateVideoSQL = "update video set title = ?, synopsis = ?, available_count = ? where id = ?";

    public List<Video> findAll(){
        return jdbc.query(findAllVideoSQL,BeanPropertyRowMapper.newInstance(Video.class));
    }

    public int updateVideo(Video video){
        Integer iResult = jdbc.update(updateVideoSQL,video.getTitle(),video.getSynopsis(),video.getAvailableCount(),video.getId());
        return iResult;
    }

    public int insertVideo(Video video){
        Integer iResult = jdbc.update(insertVideoSQL, video.getTitle(),video.getSynopsis(),video.getAvailableCount());
        return iResult;
    }

}
