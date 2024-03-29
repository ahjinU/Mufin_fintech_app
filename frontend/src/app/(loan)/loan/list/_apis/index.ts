import useFetch from '@/hooks/useFetch';

type dataType = {
  data?: any;
  api: string;
};

export default function LoanListApis() {
  const { UsePostFetch } = useFetch();

  const postLoanList = (data: dataType) => {
    const res = UsePostFetch(data);
    return res;
  };

  return { postLoanList };
}
