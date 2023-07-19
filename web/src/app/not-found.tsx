"use client";
import { useRouter } from "next/navigation";

export default function NotFound() {
  const { back } = useRouter();

  return (
    <div className="flex items-center justify-center min-h-screen flex-col">
      <h1 className="text-xl font-bold mb-2">Página não existe</h1>
      <button
        className="text-sm bg-[#2773b4] p-2 rounded-md"
        onClick={() => back()}
      >
        Voltar para a Página anterior
      </button>
    </div>
  );
}
