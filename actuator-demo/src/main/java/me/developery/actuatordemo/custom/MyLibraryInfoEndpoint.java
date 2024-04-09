package me.developery.actuatordemo.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Endpoint(id="myLibraryInfo")
public class MyLibraryInfoEndpoint {

    @WriteOperation
    public void changeSomething(String name, boolean enabledSometing){
        log.info("name: {}, enabledSometing:{}",name, enabledSometing);
    }

    @ReadOperation
    public String getPathVariable(@Selector() String path1) {
        log.info("path1: {}", path1);
        return "path1:" +path1;
    }

    @ReadOperation
    public List<LibraryInfo> getLibraryInfos(@Nullable String name, boolean includeVersion) {

        LibraryInfo libraryInfo = new LibraryInfo();
        libraryInfo.setName("logback");
        libraryInfo.setVersion("1.0.0");

        LibraryInfo libraryInfo2 = new LibraryInfo();
        libraryInfo2.setName("jackson");
        libraryInfo2.setVersion("2.0.0");

        List<LibraryInfo> libraryInfos = Arrays.asList(libraryInfo, libraryInfo2);

        if ( name != null) {
            libraryInfos = libraryInfos.stream()
                    .filter(lib -> lib.getName().equals(name))
                    .toList();
        }

        return libraryInfos;
    }
}
