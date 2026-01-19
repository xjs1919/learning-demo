package com.github.xjs.springboot4.config;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.accept.ApiVersionParser;
import org.springframework.web.accept.ApiVersionResolver;
import org.springframework.web.accept.SemanticApiVersionParser;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Enumeration;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        //configurer.useRequestHeader("X-API-VERSION");
        //configurer.usePathSegment(1);
//        configurer.useQueryParam("X-API-VERSION")
//                .addSupportedVersions("1.3", "1.6")
//        ;
//        configurer.useMediaTypeParameter(MediaType.APPLICATION_JSON, "X-API-VERSION");
//        configurer.useVersionResolver(
//                request -> {
//                    String headerVersion = request.getHeader("X-API-VERSION");
//                    if (headerVersion != null && headerVersion.length() > 0) {
//                        return headerVersion;
//                    }
//                    String parameterVersion = request.getParameter("X-API-VERSION");
//                    if (parameterVersion != null && parameterVersion.length() > 0) {
//                        return parameterVersion;
//                    }
//                    return "v1";
//                });
        configurer.setVersionParser(new MyVersionParser());
    }

    public static class MyVersionParser implements ApiVersionParser<MyVersion>{
        private static final Pattern semanticVersionPattern = Pattern.compile("^(\\d+)(\\.(\\d+))?(\\.(\\d+))?(\\.(\\d+))?$");
        @Override
        public MyVersion parseVersion(String version) {
            Assert.notNull(version, "'version' is required");

            version = skipNonDigits(version);
            Matcher matcher = semanticVersionPattern.matcher(version);
            Assert.state(matcher.matches(), "Invalid API version format");
            String major = matcher.group(1);
            String minor = matcher.group(3);
            String patch = matcher.group(5);
            String revision = matcher.group(7);

            return new MyVersion(
                    Integer.parseInt(major),
                    (minor != null ? Integer.parseInt(minor) : 0),
                    (patch != null ? Integer.parseInt(patch) : 0),
                    (revision != null ? Integer.parseInt(revision) : 0));
        }
        private static String skipNonDigits(String value) {
            for (int i = 0; i < value.length(); i++) {
                if (Character.isDigit(value.charAt(i))) {
                    return value.substring(i);
                }
            }
            return "";
        }
    }

    public static final class MyVersion implements Comparable<MyVersion>{
        private final int major;
        private final int minor;
        private final int patch;
        private final int revision;

        MyVersion(int major, int minor, int patch, int revision) {
            this.major = major;
            this.minor = minor;
            this.patch = patch;
            this.revision = revision;
        }

        @Override
        public int compareTo(MyVersion other) {
            int result = Integer.compare(this.major, other.major);
            if (result != 0) {
                return result;
            }
            result = Integer.compare(this.minor, other.minor);
            if (result != 0) {
                return result;
            }
            result = Integer.compare(this.patch, other.patch);
            if (result != 0) {
                return result;
            }
            return Integer.compare(this.revision, other.revision);
        }

        @Override
        public boolean equals(Object other) {
            return (this == other || (other instanceof MyVersion otherVersion &&
                    this.major == otherVersion.major &&
                    this.minor == otherVersion.minor &&
                    this.patch == otherVersion.patch &&
                    this.revision == otherVersion.revision));
        }

        @Override
        public int hashCode() {
            int result = this.major;
            result = 31 * result + this.minor;
            result = 31 * result + this.patch;
            result = 31 * result + this.revision;
            return result;
        }

        @Override
        public String toString() {
            return this.major + "." + this.minor + "." + this.patch + "." + this.revision;
        }
    }
}
