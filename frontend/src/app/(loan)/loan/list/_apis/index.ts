import useFetch from '@/hooks/useFetch';

type dataType = {
  data?: any;
  api: string;
};

export default function LoanListApis() {
  const { postFetch } = useFetch();

  const postLoanList = async (data: dataType) => {
    const res = await postFetch(data);
    console.log(res);
    return res;
  };

  return { postLoanList };
}
