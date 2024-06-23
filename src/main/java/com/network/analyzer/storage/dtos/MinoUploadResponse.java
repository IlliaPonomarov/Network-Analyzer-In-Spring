package com.network.analyzer.storage.dtos;

import com.network.analyzer.storage.utility.enums.PreSignedURLTypes;

public record MinoUploadResponse(String url, PreSignedURLTypes type, Metadata metadata) {
}
