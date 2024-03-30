import useFetch from '@/hooks/useFetch';

type dataType = {
  data?: any;
  api: string;
};

export default function LoanListApis() {
  const { postFetch } = useFetch();

  const postLoanList = async (data: dataType) => {
    return await postFetch(data);
  };

  return { postLoanList };
}
