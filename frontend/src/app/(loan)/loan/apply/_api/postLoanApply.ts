import useFetch from '@/hooks/useFetch';

type dataType = {
  data?: any;
  api: string;
};

export const usePostLoanApply = async (data: dataType) => {
  const { usePostFetch } = useFetch();

  console.log(data);
  const res = await usePostFetch(data);
  console.log(res);
  return res.json();
};
