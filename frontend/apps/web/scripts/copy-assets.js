import fs from "fs-extra";
import { fileURLToPath } from "url";
import { dirname, resolve } from "path";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const destDir = resolve(__dirname, "../public/assets");
const sourceDir = resolve(__dirname, "../../../../assets");

fs.ensureDirSync(destDir);

fs.copySync(sourceDir, destDir, { overwrite: true });

console.warn("Assets copied from root assets/* successfully!");
