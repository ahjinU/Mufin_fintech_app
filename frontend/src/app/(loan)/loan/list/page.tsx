'use client';

import LoanListApis from './_apis';

export default function LoanList() {
  const { postLoanList } = LoanListApis();

  const data = postLoanList({ api: '/loan/total/child' });

  return (
    <div>
      <div></div>
    </div>
  );
}
