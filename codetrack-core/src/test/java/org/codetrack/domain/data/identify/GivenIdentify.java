/*
 *  Copyright 2015 the original author or authors members of codetrack.org
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.codetrack.domain.data.identify;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author josecmoj at 15/06/15.
 */
public class GivenIdentify {

    public static int markid = 1;

    public static int pathid = 1;

    public static int contentid = 1;

    public static int contenttypeid = 1;

    public static int sourcetype = 1;

    public static int sourceid = 1;

    public static Mark getMark() {

        markid++;

        Mark result = new Mark();
        result.setId("markid" + markid);
        result.setName("markname" + markid);
        result.setDescription("markdescription" + markid);

        return result;

    }

    public static Path getPath() {

        pathid++;

        Path path = new Path();
        path.setId("pathid" + pathid);
        path.setUrl("pathurl" + pathid);

        return path;
    }

    public static Content getContent() {

        contentid++;

        Content content = new Content();
        content.setName("contentname" + contentid);
        content.setContentType(getContentType());
        content.setPath(getPath());

        return content;

    }

    public static ContentType getContentType() {

        contenttypeid++;

        ContentType contentType = new ContentType();
        contentType.setId("contenttypeid" + contenttypeid);
        contentType.setDescription("contenttypedescription" + contenttypeid);
        contentType.setExtension("EXT");

        return contentType;
    }

    public static SourceType getSourceType() {

        sourcetype++;

        SourceType sourceType = new SourceType();
        sourceType.setName("sourcetype" + sourcetype);
        sourceType.setDescription("sourcetypedescription" + sourcetype);
        sourceType.setExtension("java");

        return sourceType;

    }

    public static Source getSource() {

        sourceid++;

        return Source.newBuilder()
                .name("sourcename" + sourceid)
                .content(getContent())
                .addMark(getMark())
                .addMark(getMark())
                .description("sourcedescription" + sourceid)
                .observation("sourceobservation" + sourceid)
                .type(getSourceType())
                .url("sourceurl" + sourceid)
                .build();
    }

    public static Map<String, Source> getSourceMap() {

        Map<String, Source> result = Maps.newHashMap();

        for (int i = 0; i < 4; i++) {

            Source src = getSource();

            result.put(src.getName(), src);
        }

        return result;
    }
}
