"use client";

import { Client } from "@/models/client";
import { api } from "@/services/api";
import { parseCookies } from "nookies";
import { FormLegalPerson, FormTypeLegal } from "@/components/formLegalPerson";
import {
  FormIndividualPerson,
  FormTypeIndividual,
} from "@/components/formIndividualPerson";

interface EditClientProps {
  client: Client;
  success: () => void;
  error: (message: string) => void;
  cancel: () => void;
}

export function EditClient({
  client,
  success,
  cancel,
  error,
}: EditClientProps) {
  async function handleCreateIndividual(data: FormTypeIndividual) {
    const { token } = parseCookies();

    const clientData: Client = {
      phone: data.phone,
      individual: {
        birthday: data.birthday,
        ir: data.ir,
        name: data.name,
      },
    };

    const { status } = await api.put(
      `/client?id=${client.id}`,
      {
        ...clientData,
      },
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    status === 200 && success();
    status !== 200 && error("Não foi possivel atualizar o cliente");
  }

  async function handleCreateLegal({
    cnpj,
    corporateName,
    phone,
    trade,
  }: FormTypeLegal) {
    const { token } = parseCookies();
    const clientData: Client = {
      phone,
      legal: {
        cnpj,
        corporateName,
        trade,
      },
    };

    const { data, status } = await api.put(
      `/client?id=${client.id}`,
      {
        ...clientData,
      },
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    status === 200 && success();
    status !== 200 && error("Não foi possivel atualizar o cliente");
  }

  return (
    <div className="w-screen flex flex-col justify-center items-center min-h-screen">
      <div className="flex border-2 rounded p-2 flex-col w-3/4">
        <button
          className="bg-red-500 text-white p-1 font-bold rounded w-fit relative right-0"
          onClick={cancel}
        >
          Cancelar edição
        </button>
        {client.legal ? (
          <FormLegalPerson
            handleCreateLegal={handleCreateLegal}
            legalPerson={client}
          />
        ) : (
          <FormIndividualPerson
            handleCreateIndividual={handleCreateIndividual}
            individualPerson={client}
          />
        )}
      </div>
    </div>
  );
}
