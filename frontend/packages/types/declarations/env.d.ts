/// <reference types="vite/client" />

interface ImportMetaEnv {
    readonly VITE_DEV_API_BASE_URL: string;
    readonly VITE_DEV_API_USERNAME: string;
    readonly VITE_DEV_API_PASSWORD: string;
}

interface ImportMeta {
    readonly env: ImportMetaEnv;
}
