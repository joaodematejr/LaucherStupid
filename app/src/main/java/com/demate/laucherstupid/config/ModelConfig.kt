package com.demate.laucherstupid.config

/**
 * Configurações específicas do modelo de dispositivo
 */
object ModelConfig {

    // Configuração temporária - será substituída pelo BuildConfig quando disponível
    private const val CURRENT_MODEL = "L4" // Mude para "L3" ou "L4" conforme necessário
    private const val HAS_PREMIUM = true // Mude para false no L3

    /**
     * Obtém o tipo de modelo
     */
    fun getModelType(): String {
        return try {
            // Tentativa de usar BuildConfig quando disponível
            val buildConfigClass = Class.forName("com.demate.laucherstupid.BuildConfig")
            val field = buildConfigClass.getField("MODEL_TYPE")
            field.get(null) as String
        } catch (e: Exception) {
            CURRENT_MODEL
        }
    }

    /**
     * Verifica se recursos premium estão habilitados
     */
    fun hasPremiumFeatures(): Boolean {
        return try {
            // Tentativa de usar BuildConfig quando disponível
            val buildConfigClass = Class.forName("com.demate.laucherstupid.BuildConfig")
            val field = buildConfigClass.getField("PREMIUM_FEATURES")
            field.get(null) as Boolean
        } catch (e: Exception) {
            HAS_PREMIUM
        }
    }

    /**
     * Obtém o número de colunas do grid baseado no modelo
     */
    fun getGridColumns(): Int {
        return when (getModelType()) {
            "L3" -> 3
            "L4" -> 4
            else -> 4
        }
    }

    /**
     * Obtém o tamanho dos ícones baseado no modelo
     */
    fun getIconSize(): Int {
        return when (getModelType()) {
            "L3" -> 56
            "L4" -> 48
            else -> 48
        }
    }

    /**
     * Verifica se é modelo L3
     */
    fun isL3Model(): Boolean = getModelType() == "L3"

    /**
     * Verifica se é modelo L4
     */
    fun isL4Model(): Boolean = getModelType() == "L4"

    /**
     * Obtém configurações específicas do modelo
     */
    data class ModelSettings(
        val gridColumns: Int,
        val iconSize: Int,
        val hasPremiumFeatures: Boolean,
        val modelType: String,
        val layoutType: String
    )

    /**
     * Obtém todas as configurações do modelo atual
     */
    fun getCurrentModelSettings(): ModelSettings {
        return ModelSettings(
            gridColumns = getGridColumns(),
            iconSize = getIconSize(),
            hasPremiumFeatures = hasPremiumFeatures(),
            modelType = getModelType(),
            layoutType = if (isL4Model()) "premium" else "basic"
        )
    }
}
