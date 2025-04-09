package com.hoonsa.lim.mcp.figma

import com.hoonsa.lim.mcp.figma.api.FigmaApi
import com.hoonsa.lim.mcp.figma.config.FigmaConfig

class FigmaClientImpl(
    private val config: FigmaConfig,
    private val api: FigmaApi
) : FigmaClient {
    override suspend fun getFile(fileId: String): String {
        return api.getFile(config.accessToken, fileId)
    }

    override suspend fun getComments(fileId: String): String {
        return api.getComments(config.accessToken, fileId)
    }

    override suspend fun updateDesign(fileId: String, changes: Map<String, Any>): String {
        return api.updateDesign(config.accessToken, fileId, changes)
    }
} 