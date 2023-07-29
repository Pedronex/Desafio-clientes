"use client";

import { useRouter } from "next/navigation";
import { useForm } from "react-hook-form";
import { Client } from "@/models/client";
import { api } from "@/services/api";
import { parseCookies } from "nookies";
import { FormLegalPerson, FormTypeLegal } from "@/components/formLegalPerson";
import {
  FormIndividualPerson,
  FormTypeIndividual,
} from "@/components/formIndividualPerson";
import { validCNPJ, validCPF } from "@/utils/verifyIdentify";

export default function Create() {
  const { register, watch } = useForm<{ typeForm: "legal" | "individual" }>();
  const navigation = useRouter();
  async function handleCreateIndividual(data: FormTypeIndividual) {
    if (!validCPF(data.ir)) {
      alert("CPF Inválido");
    } else {
      const { token } = parseCookies();

      const client: Client = {
        phone: data.phone,
        individual: {
          birthday: data.birthday,
          ir: data.ir,
          name: data.name,
        },
      };

      const { status } = await api.post(
        "/client",
        {
          ...client,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      status === 201
        ? navigation.push("/home")
        : alert("Não foi possivel criar o cliente");
    }
  }

  async function handleCreateLegal(data: FormTypeLegal) {
    if (!validCNPJ(data.cnpj)) {
      alert("CNPJ Inválido");
    } else {
      const { token } = parseCookies();

      const client: Client = {
        phone: data.phone,
        legal: {
          cnpj: data.cnpj,
          corporateName: data.corporateName,
          trade: data.trade,
        },
      };

      const { status } = await api.post(
        "/client",
        {
          ...client,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      status === 201
        ? navigation.push("/home")
        : alert("Não foi possivel criar o cliente");
    }
  }

  return (
    <div className="w-screen flex flex-col justify-center items-center min-h-screen">
      <div className="flex border-2 rounded p-2 flex-col w-3/4">
        <span className="top-0 left-0 p-1 bg-blue-500 text-white w-fit rounded">
          Cadastro
        </span>
        <div className="flex flex-row w-full">
          <div className="flex flex-row mr-2">
            <input
              {...register("typeForm")}
              type="radio"
              value={"individual"}
              className="block rounded-md ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
              checked
            />
            Física
            <input
              type="radio"
              {...register("typeForm")}
              value={"legal"}
              className="block ml-1 rounded-md ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
            />
            Juridica
          </div>
        </div>
        {watch("typeForm") === "legal" ? (
          <FormLegalPerson handleCreateLegal={handleCreateLegal} />
        ) : (
          <FormIndividualPerson
            handleCreateIndividual={handleCreateIndividual}
          />
        )}
      </div>
    </div>
  );
}
