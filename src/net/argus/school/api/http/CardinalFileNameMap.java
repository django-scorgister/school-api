package net.argus.school.api.http;

import java.net.FileNameMap;

public class CardinalFileNameMap implements FileNameMap {
	
	public static final String AAC_AUDIO = "aac";
	public static final String ABI_WORD_DOCUMENT = "abw";
	public static final String ARCHIVE_DOCUMENT = "arc";
	public static final String AVIF_IMAGE = "avif";
	public static final String AUDIO_VIDEO_INTERFACE = "avi";
	public static final String AMAZON_KINDLE_EBOOK_FORMAT = "azw";
	public static final String BIN = "bin";
	public static final String BITMAP_GRAPHICS = "bmp";
	public static final String BZIP_ARCHIVE = "bz";
	public static final String BZIP2_ARCHVE = "bz2";
	public static final String CD_AUDIO = "cda";
	public static final String C_SHELL_SCRIPT = "csh";
	public static final String CASCADING_STYLE_SHEET = "css";
	public static final String COMMA_SEPARATED_VALUES = "csv";
	public static final String MICROSOFT_WORD = "doc";
	public static final String MICROSOFT_WORD_OPEN_XML = "docx";
	public static final String MS_EMBEDDED_OPEN_TYPE_FONTS = "eot";
	public static final String ELECTRONIC_PUBLICATION = "epub";
	public static final String GZIP_COMPRESSED_ARCHIVE = "gz";
	public static final String GRAPHICS_INTERCHANGE_FORMAT = "gif";
	public static final String HYPER_TEXT_MARKUP_LANGUAGE = "html";
	public static final String HYPER_TEXT_MARKUP = "htm";
	public static final String ICON_FORMAT = "ico";
	public static final String ICALENDAR_FORMAT = "ics";
	public static final String JAVA_ARCHIVE = "jar";
	public static final String JPEG_IMAGES = "jpeg";
	public static final String JPG_IMAGES = "jpg";
	public static final String JAVA_SCRIPT = "js";
	public static final String JSON_FORAT = "json";
	public static final String JSON_LD_FORMAT = "jsonld";
	public static final String MUSICAL_INSTRUMENT_DIGITAL_INTERFACE = "midi";
	public static final String MUSICAL_INSTRUMENT_DIGITAL = "mid";
	public static final String JAVASCRIPT_MODULE = "mjs";
	public static final String MP3_AUDIO = "mp3";
	public static final String MP4_VIDEO = "mp4";
	public static final String MPEG_VIDEO = "mpeg";
	public static final String APPLE_INTALLER_PACKAGE = "mpkg";
	public static final String OPEN_DOCUMENT_PRESENTATION_DOCUMENT = "odp";
	public static final String OPEN_DOCUMENT_SPREADSHEET_DOCUMENT = "ods";
	public static final String OPEN_DOCUMENT_TEXT_DOCUMENT = "odt";
	public static final String OGG_AUDIO = "oga";
	public static final String OGG_VIDEO = "ogv";
	public static final String OGG = "ogx";
	public static final String OPUS_AUDIO = "opus";
	public static final String OPEN_TYPE_FONT = "otf";
	public static final String PORTABLE_NETWORK_GRAPHICS = "png";
	public static final String ADOBE_PORTABLE_DOCUMENT_FORMAT = "pdf";
	public static final String HYPERTEXT_PREPROCESSOR= "php";
	public static final String MICROSOFT_POWER_POINT = "ppt";
	public static final String MICROSOFT_POWER_POINT_OPEN_XML = "pptx";
	public static final String RAR_ARCHIVE = "rar";
	public static final String RICH_TEXT_FORMAT = "rtf";
	public static final String BOURNE_SHELL_SCRIPT = "sh";
	public static final String SCALABLE_VECTOR_GRAPHICS = "svg";
	public static final String TAPE_ARCHIVE= "tar";
	public static final String TAGGED_IMAGE_FILE_FORMAT = "tiff";
	public static final String TAGGED_IMAGE_FILE = "tif";
	public static final String MPEG_TRANSPORT_STREAM = "ts";
	public static final String TRUE_TYPE_FONT= "ttf";
	public static final String TEXT = "txt";
	public static final String MICROSOFT_VISIO = "vsd";
	public static final String WAVEFORM_AUDIO_FORMAT = "wav";
	public static final String WEBM_AUDIO = "weba";
	public static final String WEBM_VIDEO = "webm";
	public static final String WEBM_IMAGE = "webp";
	public static final String WEB_OPEN_FONT_FORMAT = "woff";
	public static final String WEB_OPEN_FONT_FORMAT_2 = "woff2";
	public static final String XHTML = "xhtml";
	public static final String MICROSOFT_EXCEL = "xls";
	public static final String MICROSOFT_EXCEL_OPEN_XML = "xlsx";
	public static final String XML = "xml";
	public static final String XUL = "xul";
	public static final String ZIP_ARCHIVE= "zip";
	public static final String THREE_GPP = "3gp";
	public static final String THREE_GPP_2= "3g2";
	public static final String SEVEN_ZIP = "7z";

	
	public static String getExtentionByMimeType(String contentType) {
		switch(contentType.toLowerCase()) {
			case "audio/acc": return AAC_AUDIO;
	        case "application/x-abiword": return ABI_WORD_DOCUMENT;
	        case "application/x-freearc": return ARCHIVE_DOCUMENT;
	        case "image/avif": return AVIF_IMAGE;
	        case "image/x-msvideo": return AUDIO_VIDEO_INTERFACE;
	        case "application/vnd.amazon.ebook": return AMAZON_KINDLE_EBOOK_FORMAT;
	        case "application/octet-stream": return BIN;
	        case "image/bmp": return BITMAP_GRAPHICS;
	        case "application/x-bzip": return BZIP_ARCHIVE;
	        case "application/x-bzip2": return BZIP2_ARCHVE;
	        case "application/x-cdf": return CD_AUDIO;
	        case "application/x-csh": return C_SHELL_SCRIPT;
	        case "text/css": return CASCADING_STYLE_SHEET;
	        case "text/csv": return COMMA_SEPARATED_VALUES;
	        case "application/msword": return MICROSOFT_WORD;
	        case "application/vnd.openformats-officedocument.wordprocessingml.document": return MICROSOFT_WORD_OPEN_XML;
	        case "application/vnd.ms-fontobject": return MS_EMBEDDED_OPEN_TYPE_FONTS;
	        case "application/epu+zip": return ELECTRONIC_PUBLICATION;
	        case "application/gzip": return GZIP_COMPRESSED_ARCHIVE;
	        case "image/gif": return GRAPHICS_INTERCHANGE_FORMAT;
	        case "text/html": return HYPER_TEXT_MARKUP_LANGUAGE;
	        case "image/vnd.microsoft.icon": return ICON_FORMAT;
	        case "text/calendar": return ICALENDAR_FORMAT;
	        case "application/java-archive": return JAVA_ARCHIVE;
	        case "image/jpeg": return JPEG_IMAGES;
	        case "text/javascript": return JAVA_SCRIPT;
	        case "application/json": return JSON_FORAT;
	        case "application/ld+json": return JSON_LD_FORMAT;
	        case "audio/midi": return MUSICAL_INSTRUMENT_DIGITAL;
	        case "audio/mpeg": return MP3_AUDIO;
	        case "video/mp4": return MP4_VIDEO;
	        case "video/mpeg": return MPEG_VIDEO;
	        case "application/vnd.apple.installer+xml": return APPLE_INTALLER_PACKAGE;
	        case "application/vnd.oasis.opendocument.spreadsheet": return OPEN_DOCUMENT_SPREADSHEET_DOCUMENT;
	        case "application/vnd.oasis.opendocument.text": return OPEN_DOCUMENT_TEXT_DOCUMENT;
	        case "audio/ogg": return OGG_AUDIO;
	        case "video/ogg": return OGG_VIDEO;
	        case "application/ogg": return OGG;
	        case "audio/opus": return OPUS_AUDIO;
	        case "font/otf": return OPEN_TYPE_FONT;
	        case "image/png": return PORTABLE_NETWORK_GRAPHICS;
	        case "application/pdf": return ADOBE_PORTABLE_DOCUMENT_FORMAT;
	        case "application/x-httpd-php": return HYPERTEXT_PREPROCESSOR;
	        case "application/vnd.ms-powerpoint": return MICROSOFT_POWER_POINT;
	        case "application/vnd.openxmlformats-officedocument.presentationml.presentation": return MICROSOFT_POWER_POINT_OPEN_XML;
	        case "application/vnd.rar": return RAR_ARCHIVE;
	        case "application/rtf": return RICH_TEXT_FORMAT;
	        case "application/x-sh": return BOURNE_SHELL_SCRIPT;
	        case "image/svg+xml": return SCALABLE_VECTOR_GRAPHICS;
	        case "application/x-tar": return TAPE_ARCHIVE;
	        case "image/tiff": return TAGGED_IMAGE_FILE_FORMAT;
	        case "video/mp2t": return MPEG_TRANSPORT_STREAM;
	        case "font/ttf": return TRUE_TYPE_FONT;
	        case "text/plain": return TEXT;
	        case "application/vnd.visio": return MICROSOFT_VISIO;
	        case "audio/wav": return WAVEFORM_AUDIO_FORMAT;
	        case "audio/webm": return WEBM_AUDIO;
	        case "video/webm": return WEBM_VIDEO;
	        case "image/webp": return WEBM_IMAGE;
	        case "font/woff": return WEB_OPEN_FONT_FORMAT;
	        case "font/woff2": return WEB_OPEN_FONT_FORMAT_2;
	        case "application/xhtml+xml": return XHTML;
	        case "application/vnd.ms-excel": return MICROSOFT_EXCEL;
	        case "application/vnd.openxmlformats-officedocument.spreadshettml.sheet": return MICROSOFT_EXCEL_OPEN_XML;
	        case "application/xml": return XML;
	        case "application/vnd.mozilla.xul+xml": return XUL;
	        case "application/zip": return ZIP_ARCHIVE;
	        case "video/3gpp": return THREE_GPP;
	        case "video/3gpp2": return THREE_GPP_2;
	        case "application/x-7z-compressed": return SEVEN_ZIP;
	        default: return TEXT;
		}
	}
	
	@Override
	public String getContentTypeFor(String fileName) {
		switch(fileName.substring(fileName.lastIndexOf(".")+1, fileName.length())) {
			case AAC_AUDIO:	return "audio/acc";
			case ABI_WORD_DOCUMENT:	return "application/x-abiword";
			case ARCHIVE_DOCUMENT: return "application/x-freearc";
			case AVIF_IMAGE: return "image/avif";
			case AUDIO_VIDEO_INTERFACE: return "image/x-msvideo";
			case AMAZON_KINDLE_EBOOK_FORMAT: return "application/vnd.amazon.ebook";
			case BIN: return "application/octet-stream";
			case BITMAP_GRAPHICS: return "image/bmp";
			case BZIP_ARCHIVE: return "application/x-bzip";
			case BZIP2_ARCHVE: return "application/x-bzip2";
			case CD_AUDIO: return "application/x-cdf";
			case C_SHELL_SCRIPT: return "application/x-csh";
			case CASCADING_STYLE_SHEET: return "text/css";
			case COMMA_SEPARATED_VALUES: return "text/csv";
			case MICROSOFT_WORD: return "application/msword";
			case MICROSOFT_WORD_OPEN_XML: return "application/vnd.openformats-officedocument.wordprocessingml.document";
			case MS_EMBEDDED_OPEN_TYPE_FONTS: return "application/vnd.ms-fontobject";
			case ELECTRONIC_PUBLICATION: return "application/epu+zip";
			case GZIP_COMPRESSED_ARCHIVE: return "application/gzip";
			case GRAPHICS_INTERCHANGE_FORMAT: return "image/gif";
			case HYPER_TEXT_MARKUP_LANGUAGE: 
				case HYPER_TEXT_MARKUP: return "text/html";
			case ICON_FORMAT: return "image/vnd.microsoft.icon";
			case ICALENDAR_FORMAT: return "text/calendar";
			case JAVA_ARCHIVE: return "application/java-archive";
			case JPEG_IMAGES:
				case JPG_IMAGES: return "image/jpeg";
			case JAVA_SCRIPT:
				case JAVASCRIPT_MODULE: return "text/javascript";
			case JSON_FORAT: return "application/json";
			case JSON_LD_FORMAT: return "application/ld+json";
			case MUSICAL_INSTRUMENT_DIGITAL:
				case MUSICAL_INSTRUMENT_DIGITAL_INTERFACE: return "audio/midi";
			case MP3_AUDIO: return "audio/mpeg";
			case MP4_VIDEO: return "video/mp4";
			case MPEG_VIDEO: return "video/mpeg";
			case APPLE_INTALLER_PACKAGE: return "application/vnd.apple.installer+xml";
			case OPEN_DOCUMENT_SPREADSHEET_DOCUMENT: return "application/vnd.oasis.opendocument.spreadsheet";
			case OPEN_DOCUMENT_TEXT_DOCUMENT: return "application/vnd.oasis.opendocument.text";
			case OGG_AUDIO: return "audio/ogg";
			case OGG_VIDEO: return "video/ogg";
			case OGG: return "application/ogg";
			case OPUS_AUDIO: return "audio/opus";
			case OPEN_TYPE_FONT: return "font/otf";
			case PORTABLE_NETWORK_GRAPHICS: return "image/png";
			case ADOBE_PORTABLE_DOCUMENT_FORMAT: return "application/pdf";
			case HYPERTEXT_PREPROCESSOR: return "application/x-httpd-php";
			case MICROSOFT_POWER_POINT: return "application/vnd.ms-powerpoint";
			case MICROSOFT_POWER_POINT_OPEN_XML: return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
			case RAR_ARCHIVE: return "application/vnd.rar";
			case RICH_TEXT_FORMAT: return "application/rtf";
			case BOURNE_SHELL_SCRIPT: return "application/x-sh";
			case SCALABLE_VECTOR_GRAPHICS: return "image/svg+xml";
			case TAPE_ARCHIVE: return "application/x-tar";
			case TAGGED_IMAGE_FILE_FORMAT:
				case TAGGED_IMAGE_FILE: return "image/tiff";
			case MPEG_TRANSPORT_STREAM: return "video/mp2t";
			case TRUE_TYPE_FONT: return "font/ttf";
			case TEXT: return "text/plain";
			case MICROSOFT_VISIO: return "application/vnd.visio";
			case WAVEFORM_AUDIO_FORMAT: return "audio/wav";
			case WEBM_AUDIO: return "audio/webm";
			case WEBM_VIDEO: return "video/webm";
			case WEBM_IMAGE: return "image/webp";
			case WEB_OPEN_FONT_FORMAT: return "font/woff";
			case WEB_OPEN_FONT_FORMAT_2: return "font/woff2";
			case XHTML: return "application/xhtml+xml";
			case MICROSOFT_EXCEL: return "application/vnd.ms-excel";
			case MICROSOFT_EXCEL_OPEN_XML: return "application/vnd.openxmlformats-officedocument.spreadshettml.sheet";
			case XML: return "application/xml";
			case XUL: return "application/vnd.mozilla.xul+xml";
			case ZIP_ARCHIVE: return "application/zip";
			case THREE_GPP: return "video/3gpp";
			case THREE_GPP_2: return "video/3gpp2";
			case SEVEN_ZIP: return "application/x-7z-compressed";
	
			default:
				return "text/plain";
		}
	}

}
