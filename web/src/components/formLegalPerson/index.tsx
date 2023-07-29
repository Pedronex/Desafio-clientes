import { useForm } from "react-hook-form";
import ReactInputMask from "react-input-mask";
import { Client } from "@/models/client";

export type FormTypeLegal = {
  cnpj: string;
  corporateName: string;
  trade: string;
  phone: string;
};

interface FormLegalPersonProps {
  handleCreateLegal: (data: FormTypeLegal) => Promise<void>;
  legalPerson?: Client;
}

export function FormLegalPerson({
  handleCreateLegal,
  legalPerson,
}: FormLegalPersonProps) {
  const { register, handleSubmit } = useForm<FormTypeLegal>();

  return (
    <form
      onSubmit={handleSubmit(handleCreateLegal)}
      className="flex flex-row w-full flex-wrap justify-between"
    >
      <div className="w-full">
        <label htmlFor="name" className="block text-sm font-medium leading-6">
          Razão Social
        </label>
        <div className="my-2">
          <input
            {...register("corporateName")}
            type="text"
            defaultValue={legalPerson?.legal?.corporateName}
            className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
          />
        </div>
      </div>

      <div className="w-full">
        <label htmlFor="name" className="block text-sm font-medium leading-6">
          Nome Fantasia
        </label>
        <div className="my-2">
          <input
            {...register("trade")}
            type="text"
            defaultValue={legalPerson?.legal?.trade}
            className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
          />
        </div>
      </div>

      <div>
        <label htmlFor="name" className="block text-sm font-medium leading-6">
          CNPJ
        </label>
        <div className="my-2">
        <ReactInputMask
            mask={"99.999.999/9999-99"}
            maskChar=""
            {...register("cnpj")}
            type="text"
            defaultValue={legalPerson?.legal?.cnpj}
            disabled={legalPerson?.legal?.cnpj != undefined}
            className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
          />
        </div>
      </div>
      <div>
        <label htmlFor="name" className="block text-sm font-medium leading-6">
          Telefone
        </label>
        <div className="my-2">
          <ReactInputMask
            mask={"(99) 9999-9999"}
            maskChar=""
            {...register("phone")}
            type="text"
            defaultValue={legalPerson?.phone}
            className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
          />
        </div>
      </div>
      <button className="bg-blue-500 text-white p-1 font-bold rounded">
        Salvar
      </button>
    </form>
  );
}
